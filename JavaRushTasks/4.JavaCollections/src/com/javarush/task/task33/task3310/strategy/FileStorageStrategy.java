package com.javarush.task.task33.task3310.strategy;
/*
Shortener (10)
Создай и реализуй класс FileStorageStrategy. Он должен:
10.1. Реализовывать интерфейс StorageStrategy.
10.2. Использовать FileBucket в качестве ведер (англ. bucket).

Подсказка: класс должен содержать поле FileBucket[] table.

10.3. Работать аналогично тому, как это делает OurHashMapStorageStrategy, но удваивать количество ведер не когда количество элементов size станет больше какого-то порога, а когда размер одного из ведер (файлов) стал больше bucketSizeLimit.
10.3.1. Добавь в класс поле long bucketSizeLimit.
10.3.2. Проинициализируй его значением по умолчанию, например, 10000 байт.
10.3.3. Добавь сеттер и геттер для этого поля.
10.4. При реализации метода resize(int newCapacity) проследи, чтобы уже не нужные файлы были удалены (вызови метод remove()).
Проверь новую стратегию в методе main(). Учти, что стратегия FileStorageStrategy гораздо более медленная, чем остальные. Не используй большое количество элементов для теста, это может занять оооочень много времени.
Запусти программу и сравни скорость работы всех 3х стратегий.

P.S. Обрати внимание на наличие всех необходимых полей в классе FileStorageStrategy, по аналогии с OurHashMapStorageStrategy:
static final int DEFAULT_INITIAL_CAPACITY
static final long DEFAULT_BUCKET_SIZE_LIMIT
FileBucket[] table
int size
private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT
long maxBucketSize
 */
public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final  long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private int size;
    private long maxBucketSize;

    public FileStorageStrategy() {
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new FileBucket();
        }
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public int hash(Long k) {
        long h = k;
        h ^= (h >>> 20) ^ (h >>> 12);
        return (int) (h ^ (h >>> 7) ^ (h >>> 4));
    }

    public int indexFor(int hash, int length) {
        return hash % (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    public void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    public void transfer(FileBucket[] newTable) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null) {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                } else {
                    entry.next = newTable[newIndex].getEntry();
                }
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            table[i].remove();
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key)!=null;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null)
                continue;

            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (entry.value.equals(value))
                    return true;

                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            addEntry(hash, key, value, index);
        } else {
            createEntry(hash, key, value, index);
        }
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null)
                continue;

            Entry entry = table[i].getEntry();

            while (entry != null) {
                if (entry.value.equals(value))
                    return entry.key;
                entry = entry.next;
            }
        }
        return 0L;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null)
            return entry.value;

        return null;
    }
}
