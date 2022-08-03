import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(String s,GameProgress instance) {
        try (FileOutputStream fos = new FileOutputStream(s);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(instance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void zipFiles(String way, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(way));

                 FileInputStream fis = new FileInputStream(list.get(i))){
                ZipEntry entry = new ZipEntry(list.get(i));
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        sb.append("-- Создание экземпляра StringBuilder для добавления в \n" +
                "него всей информации о создании файлов и каталогов\n");
        File src = new File("src");
        src.mkdir();
        if (src.exists()) {
            sb.append("-- Успешное cоздание директории src\n");
        } else {
            sb.append("--Неуспешное cоздание директории src\n");
        }

        File res = new File("res");
        res.mkdir();
        if (res.exists()) {
            sb.append("-- Успешное cоздание директории res\n");
        } else {
            sb.append("--Неуспешное cоздание директории res\n");
        }

        File savegames = new File("savegames");
        savegames.mkdir();
        if (savegames.exists()) {
            sb.append("-- Успешное cоздание директории savegames\n");
        } else {
            sb.append("--Неуспешное cоздание директории savegames\n");
        }

        File temp = new File("temp");
        temp.mkdir();
        if (temp.exists()) {
            sb.append("-- Успешное cоздание директории temp\n");
        } else {
            sb.append("--Неуспешное cоздание директории temp\n");
        }

        File main = new File("src//main");
        main.mkdir();
        if (main.exists()) {
            sb.append("-- Успешное cоздание директории main в каталоге src\n");
        } else {
            sb.append("--Неуспешное cоздание директории main в каталоге src\n");
        }

        File test = new File("src//test");
        test.mkdir();
        if (test.exists()) {
            sb.append("-- Успешное cоздание директории test в каталоге src\n");
        } else {
            sb.append("--Неуспешное cоздание директории test в каталоге src\n");
        }


        File drawables = new File("res//drawables");
        drawables.mkdir();
        if (drawables.exists()) {
            sb.append("-- Успешное cоздание директории drawables в каталоге res\n");
        } else {
            sb.append("--Неуспешное cоздание директории drawables в каталоге res\n");
        }

        File vectors = new File("res//vectors");
        vectors.mkdir();
        if (vectors.exists()) {
            sb.append("-- Успешное cоздание директории vectors в каталоге res\n");
        } else {
            sb.append("--Неуспешное cоздание директории vectors в каталоге res\n");
        }
        File icons = new File("res/icons");
        icons.mkdir();
        if (icons.exists()) {
            sb.append("-- Успешное cоздание директории icons в каталоге res\n");
        } else {
            sb.append("--Неуспешное cоздание директории icons в каталоге res\n");
        }

        File mainJava = new File("src//main//Main.java");
        try {
            mainJava.createNewFile();
            if (mainJava.exists()) {
                sb.append("-- Успешное cоздание файла mainJava в подкаталоге main\n");
            } else {
                sb.append("--Неуспешное cоздание файла mainJava в подкаталоге main\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File utilsJava = new File("src//main//Utils.java");
        try {
            utilsJava.createNewFile();
            if (utilsJava.exists()) {
                sb.append("-- Успешное cоздание файла utilsJava в подкаталоге main\n");
            } else {
                sb.append("--Неуспешное cоздание файла utilsJava в подкаталоге main\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File tempTxt = new File("temp//temp.txt");
        try {
            tempTxt.createNewFile();
            if (tempTxt.exists()) {
                sb.append("-- Успешное cоздание файла tempTxt в каталоге temp\n");
            } else {
                sb.append("--Неуспешное cоздание файла tempTxt в каталоге temp\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        String text = sb.toString();
        try (FileWriter writer = new FileWriter("temp//temp.txt", false)) {
            writer.write(text);

            writer.append('\n');
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////

        GameProgress instance1 = new GameProgress(10000, 2, 56, 278965.78);
        GameProgress instance2 = new GameProgress(987, 7, 9, 8965.34);
        GameProgress instance3 = new GameProgress(345, 4, 32, 78965.21);

        saveGame("savegames//save.data1",instance1);
        saveGame("savegames//save.data2",instance2);
        saveGame("savegames//save.data3",instance3);

        List<String> list = new ArrayList<>();
        list.add("savegames//save.data1");
        list.add("savegames//save.data2");
        list.add("savegames//save.data3");

        zipFiles("savegames//savegames.zip", list);
    }


}
