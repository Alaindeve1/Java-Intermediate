package TASK2;
// FileProcessor.java
import java.io.*;
import java.util.*;

public class FileProcessor {
    private Scanner scanner;
    
    public FileProcessor() {
        scanner = new Scanner(System.in);
    }
    
    // Main processing method that orchestrates reading, processing, and writing
    public void processFile() {
        System.out.println("🎉 Welcome to File Processing System!");
        
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    processTextFile();
                    break;
                case 2:
                    createSampleFile();
                    break;
                case 3:
                    listFilesInDirectory();
                    break;
                case 4:
                    System.out.println("👋 Thank you for using File Processing System!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    // Display menu options
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("    FILE PROCESSING SYSTEM");
        System.out.println("=".repeat(40));
        System.out.println("1. Process Text File");
        System.out.println("2. Create Sample File");
        System.out.println("3. List Files in Current Directory");
        System.out.println("4. Exit");
        System.out.println("=".repeat(40));
        System.out.print("Choose an option (1-4): ");
    }
    
    // Main file processing logic
    private void processTextFile() {
        System.out.print("Enter the name of the file to read (e.g., input.txt): ");
        String inputFileName = scanner.nextLine();
        
        System.out.print("Enter the name for the output file (e.g., output.txt): ");
        String outputFileName = scanner.nextLine();
        
        try {
            // Read the file content
            String content = readFile(inputFileName);
            
            // Process the content
            FileStats stats = analyzeContent(content);
            
            // Write results to output file
            writeResults(outputFileName, inputFileName, stats, content);
            
            // Display results to console
            displayResults(stats);
            
        } catch (FileNotFoundException e) {
            System.out.println("❌ Error: File '" + inputFileName + "' not found.");
            System.out.println("   Please make sure the file exists in the current directory.");
        } catch (IOException e) {
            System.out.println("❌ Error occurred while processing file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }
    
    // Read content from file
    private String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        
        // Using try-with-resources for automatic resource management
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        
        return content.toString();
    }
    
    // Analyze the content and extract statistics
    private FileStats analyzeContent(String content) {
        FileStats stats = new FileStats();
        
        // Count lines
        String[] lines = content.split("\n");
        stats.lineCount = lines.length;
        
        // Count words and characters
        stats.wordCount = 0;
        stats.characterCount = content.length();
        stats.characterCountNoSpaces = content.replace(" ", "").replace("\n", "").length();
        
        // Count words by splitting on whitespace
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                String[] words = line.trim().split("\\s+");
                stats.wordCount += words.length;
            }
        }
        
        // Find most frequent words
        stats.wordFrequency = getWordFrequency(content);
        
        // Count sentences (approximate)
        stats.sentenceCount = content.split("[.!?]+").length;
        
        // Calculate average word length
        String[] allWords = content.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");
        int totalChars = 0;
        for (String word : allWords) {
            totalChars += word.length();
        }
        stats.averageWordLength = stats.wordCount > 0 ? (double) totalChars / stats.wordCount : 0;
        
        return stats;
    }
    
    // Get word frequency map
    private Map<String, Integer> getWordFrequency(String content) {
        Map<String, Integer> frequency = new HashMap<>();
        
        // Convert to lowercase and remove punctuation
        String cleanContent = content.toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
        String[] words = cleanContent.split("\\s+");
        
        for (String word : words) {
            if (!word.trim().isEmpty() && word.length() > 2) { // Ignore very short words
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }
        
        return frequency;
    }
    
    // Write analysis results to output file
    private void writeResults(String outputFileName, String inputFileName, FileStats stats, String originalContent) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            writer.println("=" + "=".repeat(50) + "=");
            writer.println("           FILE ANALYSIS REPORT");
            writer.println("=" + "=".repeat(50) + "=");
            writer.println("Input File: " + inputFileName);
            writer.println("Analysis Date: " + new Date());
            writer.println("-".repeat(52));
            
            writer.println("\n📊 BASIC STATISTICS:");
            writer.println("Lines: " + stats.lineCount);
            writer.println("Words: " + stats.wordCount);
            writer.println("Characters (with spaces): " + stats.characterCount);
            writer.println("Characters (without spaces): " + stats.characterCountNoSpaces);
            writer.println("Sentences (estimated): " + stats.sentenceCount);
            writer.printf("Average Word Length: %.2f characters%n", stats.averageWordLength);
            
            writer.println("\n🔤 TOP 10 MOST FREQUENT WORDS:");
            stats.wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .forEach(entry -> writer.println("  " + entry.getKey() + ": " + entry.getValue() + " times"));
            
            writer.println("\n📄 ORIGINAL CONTENT:");
            writer.println("-".repeat(52));
            writer.println(originalContent);
            
            System.out.println("✅ Analysis results written to: " + outputFileName);
        }
    }
    
    // Display results to console
    private void displayResults(FileStats stats) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           ANALYSIS RESULTS");
        System.out.println("=".repeat(40));
        System.out.println("📊 Lines: " + stats.lineCount);
        System.out.println("📊 Words: " + stats.wordCount);
        System.out.println("📊 Characters (with spaces): " + stats.characterCount);
        System.out.println("📊 Characters (without spaces): " + stats.characterCountNoSpaces);
        System.out.println("📊 Sentences: " + stats.sentenceCount);
        System.out.printf("📊 Average Word Length: %.2f characters%n", stats.averageWordLength);
        
        System.out.println("\n🔤 Top 5 Most Frequent Words:");
        stats.wordFrequency.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " times"));
    }
    
    // Create a sample file for testing
    private void createSampleFile() {
        System.out.print("Enter filename for sample file (e.g., sample.txt): ");
        String fileName = scanner.nextLine();
        
        String sampleContent = """
            Welcome to Java Programming!
            
            Java is a powerful, object-oriented programming language that is widely used 
            for developing various applications. It was created by James Gosling at Sun 
            Microsystems and released in 1995.
            
            Key features of Java include:
            - Platform independence (Write Once, Run Anywhere)
            - Object-oriented programming
            - Automatic memory management
            - Strong security features
            - Rich API library
            
            Java is used for:
            1. Web development (Spring Framework)
            2. Mobile app development (Android)
            3. Desktop applications
            4. Enterprise applications
            5. Big data processing
            
            Learning Java opens up many career opportunities in software development.
            Whether you're building web applications, mobile apps, or enterprise systems,
            Java provides the tools and frameworks needed for success.
            
            Happy coding with Java!
            """;
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print(sampleContent);
            System.out.println("✅ Sample file '" + fileName + "' created successfully!");
            System.out.println("   You can now process this file using option 1.");
        } catch (IOException e) {
            System.out.println("❌ Error creating sample file: " + e.getMessage());
        }
    }
    
    // List files in current directory
    private void listFilesInDirectory() {
        File currentDir = new File(".");
        File[] files = currentDir.listFiles((dir, name) -> name.endsWith(".txt"));
        
        System.out.println("\n📁 Text files in current directory:");
        if (files == null || files.length == 0) {
            System.out.println("   No .txt files found.");
        } else {
            for (File file : files) {
                System.out.println("   📄 " + file.getName());
            }
        }
    }
    
    // Inner class to hold file statistics
    private static class FileStats {
        int lineCount = 0;
        int wordCount = 0;
        int characterCount = 0;
        int characterCountNoSpaces = 0;
        int sentenceCount = 0;
        double averageWordLength = 0.0;
        Map<String, Integer> wordFrequency = new HashMap<>();
    }
    
    // Main method
    public static void main(String[] args) {
        FileProcessor processor = new FileProcessor();
        processor.processFile();
    }
}