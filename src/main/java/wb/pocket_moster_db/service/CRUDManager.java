package wb.pocket_moster_db.service;

import wb.pocket_moster_db.db.DBMonsterService;
import wb.pocket_moster_db.db.DBTrainerService;
import wb.pocket_moster_db.utility.InputUtils;

public class CRUDManager {

    private final DBTrainerService dbTrainerService;
    private final DBMonsterService dbMonsterService;

    public CRUDManager() {
        this.dbTrainerService = new DBTrainerService();
        this.dbMonsterService = new DBMonsterService();
    }

    public void printOptions() {
        System.out.println("Welcome to Pocket Monster Database manager! \n");
        while (true) {
            System.out.println("1. List all monsters.");
            System.out.println("2. List all trainers.");
            System.out.println("3. Create new monster.");
            System.out.println("4. Create new trainers.");
            System.out.println("5. Exit");
            // TODO: following comments
            /*System.out.println("Update monster.");
            System.out.println("Update trainer.");
            System.out.println("Delete monster.");
            System.out.println("Delete trainer.");
            System.out.println("A trainer catches a monster.");
            System.out.println("A monster is released into wilderness.");
            System.out.println("List monsters owned by a trainer.");
            System.out.println("List trainers by amount of monsters owned.");
            System.out.println("List wild monsters (not owned by trainers).");*/

            final int choiceOptions = InputUtils.readInt();
            switch (choiceOptions) {
                case 1 -> dbMonsterService.printAllMonsters();
                case 2 -> dbTrainerService.printAllTrainers();
                case 3 -> createMonster();
                case 4 -> createTrainer();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void createMonster() {
        System.out.println("Enter monster name: ");
        final String monsterName = InputUtils.readString();
        System.out.println("Enter monster's trainer's id: ");
        final int monsterTrainerId = InputUtils.readInt();

        if (dbMonsterService.addMonster(monsterName, monsterTrainerId) > 0) {
            System.out.println("Monster created successfully!");
        } else {
            System.out.println("Monster could not be created.");
        }

    }

    private void createTrainer() {
        System.out.println("Enter trainer name: ");
        final String trainerName = InputUtils.readString();

        if (dbTrainerService.addTrainer(trainerName) > 0) {
            System.out.println("Trainer created successfully!");
        } else {
            System.out.println("Trainer could not be created.");
        }
    }
}
