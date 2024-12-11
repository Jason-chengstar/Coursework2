public static int cardCompare(String Card1, String Card2) {
            String Card1_number, Card1_suit;
            String Card2_number, Card2_suit;
            if (Card1.length() == 2) {
                    Card1_number = Card1.substring(0, 1);
                    Card1_suit = Card1.substring(1);
            } else {
                    Card1_number = Card1.substring(0, 2);
                    Card1_suit = Card1.substring(2);
            }
            if (Card2.length() == 2) {
                    Card2_number = Card2.substring(0, 1);
                    Card2_suit = Card2.substring(1);
            } else {
                    Card2_number = Card2.substring(0, 2);
                    Card2_suit = Card2.substring(2);
            }
            HashMap<String, Integer> suit_map = new HashMap<>();
            suit_map.put("H", 1);
            suit_map.put("C", 2);
            suit_map.put("D", 3);
            suit_map.put("S", 4);
            if (suit_map.get(Card1_suit) > suit_map.get(Card2_suit)) {
                    return 1;
            } else if (suit_map.get(Card1_suit) < suit_map.get(Card2_suit)) {
                    return -1;
            } else {
                    if (Integer.valueOf(Card1_number) > Integer.valueOf(Card2_number)) {
                            return 1;
                    } else if (Integer.valueOf(Card1_number) < Integer.valueOf(Card2_number)) {
                            return -1;
                    } else {
                            return 0;
                    }
            }
    }
    public static ArrayList<String> bubbleSort(ArrayList<String> list) {
            for (int i = 0; i < list.size() - 1; i++) {
                    for (int j = 0; j < list.size() - 1 - i; j++) {
                            if (cardCompare(list.get(j), list.get(j + 1)) == 1) {
                                    Collections.swap(list, j, j + 1);
                
                            }
                    }
            }
            return list;
    }
    public static ArrayList<String> mergeSort(ArrayList<String> list) {
            if (list.size() <= 1) {
                    return list;
            }
            int mid = list.size() / 2;
            ArrayList<String> left_list = new ArrayList<>(list.subList(0, mid));
            ArrayList<String> right_list = new ArrayList<>(list.subList(mid, list.size()));
            left_list = mergeSort(left_list);
            right_list = mergeSort(right_list);
    
            return merge(left_list, right_list);
    }
    private static ArrayList<String> merge(ArrayList<String> left_list, ArrayList<String> right_list) {
            ArrayList<String> result = new ArrayList<>();
            int i = 0, j = 0;
            while (i < left_list.size() && j < right_list.size()) {
                    int compareResult = cardCompare(left_list.get(i), right_list.get(j));
                    if (compareResult <= 0) {
                            result.add(left_list.get(i));
                            i++;
                    } else {
                            result.add(right_list.get(j));
                            j++;
                    }
            }
            result.addAll(left_list.subList(i, left_list.size()));
            result.addAll(right_list.subList(j, right_list.size()));
    
            return result;
    }
    public static long MeasureBubbleSort(String filename) {
            BufferedReader br = null;
            try {
                    br = new BufferedReader(new FileReader(filename));
            } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
            }
            String line;
            ArrayList<String> cards = new ArrayList<>();
            while (true) {
                    try {
                            if (!((line = br.readLine()) != null)) break;
                    } catch (IOException e) {
                            throw new RuntimeException(e);
                    }
                    cards.add(line);
            }
            long startTime = System.currentTimeMillis();
            bubbleSort(cards);
            long endTime = System.currentTimeMillis();
            return endTime - startTime;
    
    
    }
    public static long MeasureMergeSort(String filename) {
            BufferedReader br = null;
            try {
                    br = new BufferedReader(new FileReader(filename));
        
                String line;
                ArrayList<String> cards = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                        cards.add(line);
                }
        
        
        
                long startTime = System.currentTimeMillis();
                mergeSort(cards);
                long endTime = System.currentTimeMillis();
                return endTime - startTime;
            } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
            } catch (IOException e) {
                    throw new RuntimeException(e);
            }
    
    
    }
    public static void sortComparison(String[] filelist) {
            try (FileWriter writer = new FileWriter("sortComparison.csv", true)) {
                    writer.write("         , 10, 100, 10000\n");
                    writer.write("bubbleSort");
                    for (String filename : filelist) {
                            long bubbleTime = MeasureBubbleSort(filename);
                            writer.write(String.format(",  %d", bubbleTime));
                    }
                    writer.write("\n");
                    writer.write("mergeSort");
                    for (String filename : filelist) {
                            long mergeTime = MeasureMergeSort(filename);
                            writer.write(String.format(",  %d", mergeTime));
                    }
                    writer.write("\n");
        
            } catch (IOException e) {
                    e.printStackTrace();
            }
            try (BufferedReader br = new BufferedReader(new FileReader("sortComparison.csv"))) {
             String line;
             while ((line = br.readLine())!= null) { System.out.println(line); } }
         catch (IOException e) { e.printStackTrace(); }

    }
sortComparison(new String[]{"C:\\Users\\JSC\\Desktop\\coursework2_files\\sort10.txt","C:\\Users\\JSC\\Desktop\\coursework2_files\\sort100.txt","C:\\Users\\JSC\\Desktop\\coursework2_files\\sort10000.txt"});