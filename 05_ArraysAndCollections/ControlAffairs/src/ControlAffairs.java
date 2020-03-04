import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class ControlAffairs {

  private static void printUsage() {
    System.out.println("Перечень возможных команд: LIST, ADD, EDIT, DELETE ");
    System.out.println(
        "LIST - вводит дело с порядковым номером.\nADD - добавляет дело в конец списка или дело на определённое место, сдвигая остальные дела вперёд, если указать номер.");
    System.out.println(
        "EDIT - заменяет дело с указанным номером.\nDELETE - удаляет дело с указанным номером.");
    System.out.println("Примеры команд:\n"
        + "    LIST\n"
        + "    ADD Какое-то дело\n"
        + "    ADD 4 Какое-то дело на четвёртом месте\n"
        + "    EDIT 3 Новое название дела\n"
        + "    DELETE 7");
    System.out.println("Если Вы хотите прекратить выполнение программы, наберите команду: END ");
  }


  public static void main(String[] args) {
    List<String> toDoList = new ArrayList<>();

    toDoList.add("Дело №2");
    toDoList.add(0, "Дело №1");
    toDoList.add("Дело №3");
    toDoList.add("Дело №4");
    toDoList.add("Дело №5");
    toDoList.add("Дело №6");
    toDoList.add("Дело №7");
    toDoList.add("Дело №8");
    toDoList.add("Дело №9");
    toDoList.add("Дело №10");

    printUsage();

    Scanner scanner = new Scanner(System.in);   // Организуем ввод команды с клавиатуры

    while (true) {

      System.out.print("Введите команду : ");
      String input = scanner.nextLine();
      try {
        Command command = parseCommand(input);
        command.apply(toDoList);
        if (command.isTerminate()) {
          return;
        }
      } catch (Exception x) {
        System.out.println("Ошибка: " + x.getMessage());
        printUsage();
      }
    }
  }


  private static void requireParamsCount(String action, String[] args, int required) {
    if (args.length != required) {
      throw new IllegalStateException("Для команды " + action + " требуется " + required + " параметр(ов), но передано " + args.length);
    }
  }

  private static String[] splitByFirstSpace(String input) {
    int spaceIndex = input.indexOf(' ');
    if (spaceIndex == -1) {
      // команда без аргументов
      return new String[]{input};
    } else {
      String action = input.substring(0, spaceIndex);
      String args = input.substring(spaceIndex + 1).trim();
      return new String[]{action, args};
    }
  }

  private static Command parseCommand(String input) {
    Objects.requireNonNull(input); // проверка на null
    String[] tokens = splitByFirstSpace(input); // разобъем строку на команду и аргументы
    String action = tokens[0];
    String[] args = tokens.length > 1 ? splitByFirstSpace(tokens[1]) : new String[0];

    if ("END".equals(action)) {
      requireParamsCount(action, args, 0);
      return new ExitCommand();
    } else if ("LIST".equals(action)) {
      requireParamsCount(action, args, 0);
      return new ListCommand();
    } else if ("DELETE".equals(action)) {
      requireParamsCount(action, args, 1);
      int index = Integer.parseInt(args[0]) - 1;
      return new DeleteCommand(index);
    } else if ("EDIT".equals(action)) {
      requireParamsCount(action, args, 2);
      int index = Integer.parseInt(args[0]) - 1;
      String text = args[1];
      return new EditCommand(index, text);
    } else if ("ADD".equals(action)) {
      // здесь нельзя однозначно сказать, сколько нам нужно аргументов, так как
      // комманда имеет две формы с индексом для вставки и без
      // так же парсинг усложняется тем, что разделения аргументов команды используется пробел,
      // который может входить как символ в строковое значения аргумента

      if (args.length == 2) {
        try {
          int index = Integer.parseInt(args[0]) - 1;
          String text = args[1];
          return new AddToIndexCommand(index, text);
        } catch (NumberFormatException ignore) {
          // значит на самом деле это форма команды с одним аргументом в которм содержится пробел
          // строчка с неразбитыми аргументами хранится в tokens[1]
          return new AddCommand(tokens[1]);
        }
      } else if (args.length == 1) {
        return new AddCommand(args[0]);
      }
      // сюда мы попадем только если args.length == 0
      throw new IllegalStateException("Для команды " + action + " требуется как минимум 1 параметр");
    } else {
      throw new IllegalStateException("Команда не распознана: " + action);
    }
  }


  interface Command {
    void apply(List<String> toDoList);

    default boolean isTerminate() {
      return false;
    }
  }

  static class ExitCommand implements Command {
    @Override
    public void apply(List<String> toDoList) {
      System.out.println("Работа программы окончена");
    }
  }

  static class ListCommand implements Command {
    @Override
    public void apply(List<String> toDoList) {
      if (toDoList.isEmpty()) {
        System.out.println("Список пуст");
      } else {
        for (int i = 0; i < toDoList.size(); i++) {
          System.out.printf("%d - %s%n", i + 1, toDoList.get(i));
        }
      }
    }
  }

  static class AddToIndexCommand implements Command {
    private final int index;
    private final String text;

    public AddToIndexCommand(int index, String text) {
      this.index = index;
      this.text = text;
    }

    @Override
    public void apply(List<String> toDoList) {
      if (index > toDoList.size() || index < 0) {
        System.out.println("Параметр команды ADD введен неправильно. Введите еще раз");
      } else {
        toDoList.add(index, text);
      }
    }
  }

  static class EditCommand implements Command {
    private final int index;
    private final String text;

    public EditCommand(int index, String text) {
      this.index = index;
      this.text = text;
    }

    @Override
    public void apply(List<String> toDoList) {
      if (index >= toDoList.size() || index < 0) {
        System.out.println("Параметр команды EDIT введен неправильно. Введите еще раз");
      } else {
        toDoList.set(index, text);
      }

    }
  }

  static class AddCommand implements Command {
    private final String text;

    public AddCommand(String text) {
      this.text = text;
    }

    @Override
    public void apply(List<String> toDoList) {
      toDoList.add(text);
    }
  }

  static class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
      this.index = index;
    }

    @Override
    public void apply(List<String> toDoList) {
      if (index < 0 || index >= toDoList.size()) {
        System.out.println("Параметр команды DELETE введен неправильно. Введите еще раз");
      } else {
        toDoList.remove(index);
      }
    }
  }
}

