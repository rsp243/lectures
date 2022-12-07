package src.classes;

import java.util.Arrays;

import src.enums.*;
import src.interfaces.*;
import java.lang.Math;
import java.lang.Object;

public class Person extends Creature implements ContollingClothesAction,
                            ControllingEmotionsAction,
                            SpeakingAction,  
                            AbleToSleep, 
                            DetainAction, 
                            VehicleAction{
    private String secondName;
    private Clothes[] arrayOfClothes;
    private EmotionType[] arrayOfEmotions;
    private Freedom freedom;

    public Person(String name) {
        this(name, "", new Clothes[5],
        new EmotionType[5], Freedom.FREE, new Coordinate(0, 0), 5);
    }
    
    public Person(String name, String secondName, Clothes[] arrayOfClothes, EmotionType[] arrayOfEmotions,
            Freedom freedom, Coordinate currentCoordinates, int speed) {
        super(name, currentCoordinates, speed);
        this.secondName = secondName;
        this.arrayOfClothes = arrayOfClothes;
        this.arrayOfEmotions = arrayOfEmotions;
        this.freedom = freedom;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
        result = prime * result + Arrays.hashCode(arrayOfClothes);
        result = prime * result + Arrays.hashCode(arrayOfEmotions);
        result = prime * result + ((freedom == null) ? 0 : freedom.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (secondName == null) {
            if (other.secondName != null)
                return false;
        } else if (!secondName.equals(other.secondName))
            return false;
        if (!Arrays.equals(arrayOfClothes, other.arrayOfClothes))
            return false;
        if (!Arrays.equals(arrayOfEmotions, other.arrayOfEmotions))
            return false;
        if (freedom != other.freedom)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Person [secondName=" + secondName + ", arrayOfClothes=" + Arrays.toString(arrayOfClothes)
                + ", arrayOfEmotions=" + Arrays.toString(arrayOfEmotions) + ", freedom=" + freedom + "]";
    }
    public String getSecondName() {
        return secondName;
    }
    public String getArrayOfClothes() {
        String[] arrayOfNamedClothes = new String[arrayOfClothes.length];
        int position = 0;
        for (Clothes iter : arrayOfClothes) {
            if (iter != null) {
                arrayOfNamedClothes[position] = iter.getName();
            } else {
                arrayOfNamedClothes[position] = "null";
            }
            position += 1;
        }
        return Arrays.toString(arrayOfNamedClothes);
    }
    public void setArrayOfClothes(Clothes[] arrayOfClothes) {
        this.arrayOfClothes = arrayOfClothes;
    }
    public String getArrayOfEmotions() {   
        String[] arrayOfNamedEmotions = new String[arrayOfEmotions.length];
        int position = 0;
        for (EmotionType iter : arrayOfEmotions) {
            if (iter != null) {
                arrayOfNamedEmotions[position] = iter.getName();
            } else {
                arrayOfNamedEmotions[position] = "null";
            }
            position += 1;
        }
        return Arrays.toString(arrayOfNamedEmotions);
    }
    public void setArrayOfEmotions(EmotionType[] arrayOfEmotions) {
        this.arrayOfEmotions = arrayOfEmotions;
    }
    public Freedom getFreedom() {
        return freedom;
    }
    public void setFreedom(Freedom freedom) {
        this.freedom = freedom;
    }

    @Override
    public Controller ControlFreedom(Person targetPerson, Freedom freedom) {
        if (Math.random() < 0.5) {
            return Controller.FAILED;
        } else {
            targetPerson.setFreedom(freedom);
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller ControlFreedom(PersonGroup targetGroup, Freedom freedom) {
        return Controller.FAILED;
    }
    @Override
    public Controller Sleep(Location location) {
        return Controller.SUCCESSFULLY;
    }
    @Override
    public boolean speakTo(Message messageObj, Person targetPerson) {
        return messageObj.getIsMessageTruly();
    }
    @Override
    public Controller addEmotion(Person person, EmotionType emotion) {
        int position = 0;
        for (EmotionType iter : person.arrayOfEmotions) {
            if (iter != null) {
                position += 1;
            }
        }
        if (position >= person.arrayOfEmotions.length) {
            return Controller.FAILED;
        } else {
            person.arrayOfEmotions[position] = emotion;
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller removeEmotion(Person person, EmotionType emotion) {
        int position = 0;
        for (EmotionType iter : person.arrayOfEmotions) {
            if (iter != emotion) {
                position += 1;
            } else {
                person.arrayOfEmotions[position] = null;
            }
        }
        if (position == person.arrayOfEmotions.length) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller addEmotion(EmotionType emotion) {
        int position = 0;
        for (EmotionType iter : arrayOfEmotions) {
            if (iter != null) {
                position += 1;
            }
        }
        if (position >= arrayOfEmotions.length) {
            return Controller.FAILED;
        } else {
            arrayOfEmotions[position] = emotion;
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller removeEmotion(EmotionType emotion) {
        int position = 0;
        int countDeletedEmotions = 0;
            for (EmotionType iter : arrayOfEmotions) {
            if (iter == emotion) {
                arrayOfEmotions[position] = null;
                countDeletedEmotions++;
            }
            position++;
        }
        if (countDeletedEmotions == 0) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller putOnClothes(Clothes clothes) {
        int position = 0;
        for (Clothes iter : arrayOfClothes) {
            if (iter != null) {
                position += 1;
            }
        }
        if (position >= arrayOfClothes.length) {
            return Controller.FAILED;
        } else {
            arrayOfClothes[position] = clothes;
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller takeOffClothes(Clothes clothes) {
        int position = 0;
        int countDeletedClothes = 0;
        for (Clothes iter : arrayOfClothes) {
            if (iter == clothes) {
                arrayOfClothes[position] = null;
                countDeletedClothes++;
            }
            position++;
        }
        if (countDeletedClothes == 0) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public void getInTheCar(Person person, Vehicle vehicle) {
        
    }
    @Override
    public void getOutTheCar(Person person, Vehicle vehicle) {
        
    }


    
}