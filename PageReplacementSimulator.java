import java.util.*;

public class PageReplacementSimulator {

    public static int fifo(int[] pages, int frames) {
        Queue<Integer> memory = new LinkedList<>();
        int faults = 0;
        
        for (int page : pages) {
            if (!memory.contains(page)) {
                if (memory.size() < frames) {
                    memory.add(page);
                } else {
                    memory.poll(); // Remove o primeiro elemento inserido
                    memory.add(page);
                }
                faults++;
            }
        }
        return faults;
    }

    public static int lru(int[] pages, int frames) {
        LinkedHashMap<Integer, Integer> memory = new LinkedHashMap<>(frames, 0.75f, true);
        int faults = 0;

        for (int page : pages) {
            if (!memory.containsKey(page)) {
                if (memory.size() < frames) {
                    memory.put(page, page);
                } else {
                    Integer lruPage = memory.entrySet().iterator().next().getKey();
                    memory.remove(lruPage);
                    memory.put(page, page);
                }
                faults++;
            } else {
                memory.get(page); // Acesso para atualizar a ordem
            }
        }
        return faults;
    }

    public static int optimal(int[] pages, int frames) {
        List<Integer> memory = new ArrayList<>();
        int faults = 0;
        
        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (!memory.contains(page)) {
                if (memory.size() < frames) {
                    memory.add(page);
                } else {
                    Map<Integer, Integer> futureUse = new HashMap<>();
                    for (int p : memory) {
                        int nextUse = Integer.MAX_VALUE;
                        for (int j = i + 1; j < pages.length; j++) {
                            if (pages[j] == p) {
                                nextUse = j;
                                break;
                            }
                        }
                        futureUse.put(p, nextUse);
                    }
                    int pageToRemove = Collections.max(futureUse.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
                    memory.remove((Integer) pageToRemove);
                    memory.add(page);
                }
                faults++;
            }
        }
        return faults;
    }

    public static int clock(int[] pages, int frames) {
        int[] memory = new int[frames];
        int[] useBit = new int[frames];
        Arrays.fill(memory, -1);
        int faults = 0, pointer = 0;
        
        for (int page : pages) {
            boolean found = false;
            for (int i = 0; i < frames; i++) {
                if (memory[i] == page) {
                    useBit[i] = 1;
                    found = true;
                    break;
                }
            }
            if (!found) {
                while (useBit[pointer] == 1) {
                    useBit[pointer] = 0;
                    pointer = (pointer + 1) % frames;
                }
                memory[pointer] = page;
                useBit[pointer] = 1;
                pointer = (pointer + 1) % frames;
                faults++;
            }
        }
        return faults;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter page sequence (space-separated): ");
        String[] pageStrings = scanner.nextLine().split(" ");
        int[] pages = Arrays.stream(pageStrings).mapToInt(Integer::parseInt).toArray();

        System.out.print("Enter number of frames: ");
        int frames = scanner.nextInt();
        
        int fifoFaults = fifo(pages, frames);
        int lruFaults = lru(pages, frames);
        int optimalFaults = optimal(pages, frames);
        int clockFaults = clock(pages, frames);
        
        System.out.println("Método FIFO - " + fifoFaults + " faltas de página");
        System.out.println("Método LRU - " + lruFaults + " faltas de página");
        System.out.println("Método Ótimo - " + optimalFaults + " faltas de página");
        System.out.println("Método Relógio - " + clockFaults + " faltas de página");
        
        scanner.close();
    }
}
