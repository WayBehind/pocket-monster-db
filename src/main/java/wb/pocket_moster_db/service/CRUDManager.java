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
            System.out.println("########");
            System.out.println("1. List all monsters.");
            System.out.println("2. List all trainers.");
            System.out.println("3. Show specific monster.");
            System.out.println("4. Show specific trainer.");
            System.out.println("5. Create new monster.");
            System.out.println("6. Create new trainers.");
            System.out.println("7. Edit monster.");
            System.out.println("8. Edit trainers.");
            System.out.println("9. Delete monster.");
            System.out.println("10. Delete trainer.");
            System.out.println("11. List trainer and monsters they own.");
            System.out.println("12. List wild monsters (not owned by trainers).");
            System.out.println("13. A trainer catches a monster.");
            System.out.println("14. A monster is released into wilderness.");
            System.out.println("15. List trainers by amount of monsters owned.");
            System.out.println("16. Exit");
            System.out.println("########");

            final int choiceOptions = InputUtils.readInt();
            switch (choiceOptions) {
                case 1 -> dbMonsterService.listAllMonsters();
                case 2 -> dbTrainerService.listAllTrainers();
                case 3 -> createMonster();
                case 4 -> listThisMonster();
                case 5 -> listThisTrainer();
                case 6 -> createTrainer();
                case 7 -> editMonster();
                case 8 -> editTrainer();
                case 9 -> removeMonster();
                case 10 -> removeTrainer();
                case 11 -> dbTrainerService.printTrainersMonsters();
                case 12 -> dbMonsterService.printWildMonsters();
                case 13 -> trainerCatchesMonster();
                case 14 -> makeMonsterWild();
                case 15 -> dbTrainerService.printTrainersByMonsterAmount();
                case 16 -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // TODO: Errors if id not found

    private void listThisMonster() {
        System.out.println("ID of monster you want to see: ");
        final int monsterId = InputUtils.readInt();

        dbMonsterService.printMonster(monsterId);
    }

    private void listThisTrainer() {
        System.out.println("ID of trainer you want to see: ");
        final int trainerId = InputUtils.readInt();

        dbTrainerService.printTrainer(trainerId);
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

    private void editMonster() {
        System.out.println("ID of monster you want to edit: ");
        final int monsterId = InputUtils.readInt();
        System.out.println("Edit monster name: ");
        final String monsterName = InputUtils.readString();
        System.out.println("Edit monster's trainer ID: ");
        final int monsterTrainerId = InputUtils.readInt();

        if (dbMonsterService.updateMonster(monsterId, monsterName, monsterTrainerId) > 0) {
            System.out.println("Monster edited successfully!");
        } else {
            System.out.println("Monster could not be edited.");
        }
    }

    private void editTrainer() {
        System.out.println("ID of trainer you want to edit: ");
        final int trainerId = InputUtils.readInt();
        System.out.println("Edit trainer name: ");
        final String trainerName = InputUtils.readString();

        if (dbTrainerService.updateTrainer(trainerName, trainerId) > 0) {
            System.out.println("Trainer edited successfully!");
        } else {
            System.out.println("Trainer could not be edited.");
        }
    }

    private void removeMonster() {
        System.out.println("ID of monster you want to delete: ");
        final int monsterId = InputUtils.readInt();

        if (dbMonsterService.deleteMonster(monsterId) > 0) {
            System.out.println("Monster deleted successfully!");
        } else {
            System.out.println("Monster could not be deleted.");
        }
    }

    private void removeTrainer() {
        System.out.println("ID of trainer you want to delete: ");
        final int trainerId = InputUtils.readInt();

        if (dbTrainerService.deleteTrainer(trainerId) > 0) {
            System.out.println("Trainer deleted successfully!");
        } else {
            System.out.println("Trainer could not be deleted.");
        }
    }

    private void trainerCatchesMonster() {
        System.out.println("ID of monster to catch: ");
        final int monsterId = InputUtils.readInt();
        System.out.println("ID of trainer that catches: ");
        final int trainerId = InputUtils.readInt();

        if (dbMonsterService.cachMonster(monsterId, trainerId) > 0) {
            System.out.println("Monster caught successfully!");
        } else {
            System.out.println("Monster could not be caught.");
        }
    }

    private void makeMonsterWild() {
        System.out.println("ID of monster to release into wilderness: ");
        final int monsterId = InputUtils.readInt();

        if (dbMonsterService.releaseMonster(monsterId) > 0) {
            System.out.println("Monster released successfully!");
        } else {
            System.out.println("Monster could not be released.");
        }
    }
}
