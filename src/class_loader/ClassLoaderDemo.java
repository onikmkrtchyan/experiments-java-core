package class_loader;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        // Example 1: Checking class loaders for different classes
        System.out.println("=== Class Loader Hierarchy ===");
        
        // Bootstrap class loader (returns null)
        Class<?> stringClass = String.class;
        ClassLoader stringClassLoader = stringClass.getClassLoader();
        System.out.println("String class loader: " + stringClassLoader); // null
        
        // Extension class loader
        Class<?> sqlDriverClass = java.sql.Driver.class;
        ClassLoader sqlClassLoader = sqlDriverClass.getClassLoader();
        System.out.println("Driver class loader: " + sqlClassLoader);
        
        // Application class loader
        Class<?> currentClass = ClassLoaderDemo.class;
        ClassLoader appClassLoader = currentClass.getClassLoader();
        System.out.println("Current class loader: " + appClassLoader);
        
        // Example 2: Traversing the class loader hierarchy
        System.out.println("\n=== Class Loader Parent Chain ===");
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        while (loader != null) {
            System.out.println("Loader: " + loader);
            loader = loader.getParent();
        }
        System.out.println("Parent of Extension ClassLoader: " + loader); // null (Bootstrap)
        
        // Example 3: Loading classes dynamically
        System.out.println("\n=== Dynamic Class Loading ===");
        try {
            // Using current class loader
            Class<?> dynamicClass = Class.forName("java.util.ArrayList");
            System.out.println("Loaded: " + dynamicClass.getName());
            System.out.println("Loaded by: " + dynamicClass.getClassLoader());
            
            // Loading with specific class loader
            ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
            Class<?> loadedClass = systemLoader.loadClass("java.util.HashMap");
            System.out.println("Loaded: " + loadedClass.getName());
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        // Example 4: Checking core Java classes (loaded by Bootstrap)
        System.out.println("\n=== Bootstrap Loaded Classes ===");
        Class<?>[] coreClasses = {
            Object.class,
            String.class,
            Integer.class,
            System.class,
            Class.class
        };
        
        for (Class<?> clazz : coreClasses) {
            System.out.println(clazz.getName() + " -> " + clazz.getClassLoader());
        }
    }
}

// Custom Class Loader Example
class CustomClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading class: " + name);
        
        // First, check if the class has already been loaded
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass != null) {
            return loadedClass;
        }
        
        // Delegate to parent (following delegation model)
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            // Parent couldn't load it, try custom loading logic here
            // For this example, we'll just throw the exception
            throw e;
        }
    }
    
    // Example usage
    public static void demonstrateCustomLoader() {
        CustomClassLoader customLoader = new CustomClassLoader();
        try {
            Class<?> clazz = customLoader.loadClass("java.lang.String");
            System.out.println("Custom loader loaded: " + clazz.getName());
            System.out.println("Actual loader: " + clazz.getClassLoader()); // Still null (Bootstrap)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}