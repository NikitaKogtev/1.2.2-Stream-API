import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        int count = (int) persons.stream()
                .filter(x -> x.getAge() > 18)
                .count();
        System.out.println(count);

        List<String> militaryMan = persons.stream()
                .filter((x -> x.getAge() >= 18 && x.getAge() < 27 && x.getSex() == Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> worker = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() < 60 && x.getEducation() == Education.HIGHER && x.getSex() == Sex.WOMAN
                        || x.getAge() >= 18 && x.getAge() < 65 && x.getEducation() == Education.HIGHER && x.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .collect(Collectors.toList());

    }
}
