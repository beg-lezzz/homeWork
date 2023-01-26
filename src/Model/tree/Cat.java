package Model.tree;

import java.util.ArrayList;
import java.util.List;

import static Model.Service.agePostfix;

public class Cat extends Creature<Cat> implements Containerable {
    public Cat(String fullName, Gender gender, int age, Cat father, Cat mother) {
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.father = father;
        this.mother = mother;
        this.addChild();
    }

    public void addChild(){
        if (father != null) {this.father.children.add(this);}
        if (mother != null) {this.mother.children.add(this);}
    }

    public Cat(String fullName, Gender gender, int age) {
        this(fullName, gender, age, null, null);
    }

    public Cat(String fullName, Gender gender, int age, Cat cat) {
            this(fullName, gender, age,
                    gender == Gender.Male ? cat : null, gender == Gender.Female ? cat : null);
    }

    public void setFather(Cat father) {
        if (this.father == null) {
            this.father = father;
            this.father.children.add(this);
        } else {
            System.out.printf("У %s уже указан отец - %s", this.fullName, this.father.fullName);
        }
    }

    public void setMother(Cat mother) {
        if (this.mother == null) {
            this.mother = mother;
            this.mother.children.add(this);
        } else {
            System.out.printf("У %s уже указана мать - %s", this.fullName, this.mother.fullName);
        }
    }
    @Override
    public Cat getFather() { return father;}

    @Override
    public Cat getMother() {
        return mother;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public List<Cat> getChildren() {
        return this.children;
    }

    @Override
    public int getAge() {
        return age;
    }

    public List<Cat> getSistersBrothers(){
        List<Cat> byMother = this.getMother() != null ? this.getMother().getChildren() : null;
        List<Cat> byFather = this.getFather() != null ? this.getFather().getChildren() : null;
        List<Cat> all = new ArrayList<>();

        if (byFather != null) {
            for (Cat human : byFather) {
                if (!human.getFullName().equals(this.fullName)) {
                    all.add(human);
                }
            }
        }

        if (byMother != null) {
            for (Cat human : byMother) {
                if (!human.getFullName().equals(this.fullName)) {
                    all.add(human);
                }
            }
        }
        return all;
    }

    @Override
    public String toString() {
        String genString = gender == Gender.Male ? "муж." : "жен.";
        return  fullName + ", " + genString + ", " + age + " " + agePostfix(age);

    }

    @Override
    public String getVisibleName() {
        return this.fullName;
    }

    @Override
    public String getVisibleType() {
        return "Кот";
    }
}
