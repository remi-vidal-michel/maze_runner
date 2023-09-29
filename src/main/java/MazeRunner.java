import maze.Maze;
import maze.exceptions.MazeGenerationException;

public class MazeRunner {
    public static void main(String[] args) {
        try {
            if (args.length != 4) {
                printUsage();
                return;
            }

            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            String generationType = args[2];
            String generatorMethod = args[3];

            if (width < 5 || height < 5) {
                System.err.println("Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 5");
                printUsage();
                return;
            }

            if (!isValidGenerationType(generationType) || !isValidGeneratorMethod(generatorMethod)) {
                System.err.println("Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.");
                printUsage();
                return;
            }

            new Maze(width, height, generationType, generatorMethod);
        } catch (NumberFormatException e) {
            System.err.println("Erreur : Veuillez fournir des nombres valides pour la largeur et la hauteur.");
            printUsage();
        } catch (MazeGenerationException e) {
            System.err.println("Erreur inattendue lors de la génération du labyrinthe. Veuillez réessayer.");
        }
    }

    private static void printUsage() {
        System.err.println("Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]");
    }

    private static boolean isValidGenerationType(String type) {
        return type.equals("perfect") || type.equals("imperfect");
    }

    private static boolean isValidGeneratorMethod(String method) {
        return method.equals("simple") || method.equals("graph") || method.equals("optimized");
    }
}
