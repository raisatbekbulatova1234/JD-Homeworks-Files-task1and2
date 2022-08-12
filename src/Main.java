import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(String s, GameProgress instance) {
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

                 FileInputStream fis = new FileInputStream(list.get(i))) {
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

        List<String> directories = Arrays.asList("C:\\Games\\src", "C:\\Games\\res", "C:\\Games\\savegames", "C:\\Games/temp", "C:\\Games\\src\\main", "C:\\Games\\src\\test", "C:\\Games\\res\\drawables", "C:\\Games\\res\\vectors", "C:\\Games\\res\\icons");
        List<String> files = Arrays.asList("C:\\Games\\src\\main\\Main.java", "C:\\Games\\src\\main\\Utils.java", "C:\\Games\\temp\\temp.txt");


        sb.append("-- Создание экземпляра StringBuilder для добавления в \n" +
                "него всей информации о создании файлов и каталогов\n");

        for (int i = 0; i < directories.size(); i++) {
            File newFile = new File(directories.get(i));
            newFile.mkdir();
            if (newFile.exists()) {
                sb.append("-- Успешное cоздание директории " + newFile.getName() + " в папке " + newFile.getParent() + "\n");
            } else {
                sb.append("--Неуспешное cоздание директории " + newFile.getName() + " в папке " + newFile.getParent() + "\n");

            }

        }

        for (int i = 0; i < files.size(); i++) {
            File newFailJava = new File(files.get(i));
            try {
                newFailJava.createNewFile();
                if (newFailJava.exists()) {
                    sb.append("-- Успешное cоздание файла " + newFailJava.getName() + " в подкаталоге " + newFailJava.getParent() + "\n");
                } else {
                    sb.append("--Неуспешное cоздание файла " + newFailJava.getName() + " в подкаталоге " + newFailJava.getParent() + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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

        saveGame("savegames//save.data1", instance1);
        saveGame("savegames//save.data2", instance2);
        saveGame("savegames//save.data3", instance3);

        List<String> list = new ArrayList<>();
        list.add("savegames//save.data1");
        list.add("savegames//save.data2");
        list.add("savegames//save.data3");

        zipFiles("savegames//savegames.zip", list);
    }

}

