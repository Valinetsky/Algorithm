import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(123, "Яковлев Яков Яковлевич", "+79991111111"));
        contactList.addToEnd(new Contact(234, "Петров Петр Петрович", "+79992222222"));
        contactList.addToEnd(new Contact(345, "Иванов Иван Иванович", "+79993333333"));
        contactList.addToEnd(new Contact(456, "Борисов Борис Борисович", "+79994444444"));
        contactList.addToEnd(new Contact(567, "Александрова Александра Александровна", "+79995555555"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }

        System.out.println("------------------------------------------");
        System.out.println("---------- РАЗВОРАЧИВАЕМ СПИСОК ----------");
        System.out.println("------------------------------------------");

        contactList.reverse();

        for (Object contact : contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    /**
     * Класс списка
     *
     * @param <T>
     */
    public static class SingleLinkList<T> implements Iterable {
        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        /**
         * Класс отдельного элемента
         *
         * @param <T>
         */
        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // Проверка на то, пустая ли голова
        public boolean isEmpty() {
            return head == null;
        }

        // Заполнение списка
        public void addToEnd(T item) {

            // Выделение памяти для списка
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;

            // Если голова и хвост пустая, то присваеваем newItem
            if (isEmpty()) {
                head = newItem;
                tail = newItem;

            // Если не пустая, то передаём элементу адрес и ставим его в хвост
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        // Метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) { // Если не пусто и голова не равна нулю
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}