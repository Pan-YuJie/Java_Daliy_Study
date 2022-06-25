package www.panyujie.Mult_thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-07
 * Time: 20:46
 */

/**
 *      实现一个线程安全的单例模式
 *
 *      单例模式 ： 代码中的某个类，只能有一个实例，不能有多个  //比如 JDBC的数据源 DataSource
 *
 *      常见 :
 *      1. 饿汉模式  着急的创建实例
 *      2. 懒汉模式  用到才创建
 *
 *      线程安全的单例模式： 在多线程的情况下，并发调用 getInstance，不出BUG
 *
 */

//  1. 饿汉模式 --》 线程安全
class Singleton {

    // 1. 使用 static 创建一个实例， 并且对他进行实例化
    //  这个instance 对应的实例，就是该类的唯一实例
    private static Singleton instance= new Singleton();  // 类加载的时候 new 其实例

    // 2. 防止程序员在其他 new 实例
    private Singleton() {

    }

    // 提供一个方法， 使外界能拿到这个实例
    public static Singleton getInstance(){
        return instance;
    }

}

// 2.懒汉模式 --》 线程不安全
class Singleton2{

    // 并不是一开始就创建实例
    private static Singleton2 instance = null;

    private Singleton2(){
    }

    public static Singleton2 getInstance(){
        //使用到时候才 new 一个实例
        if(instance==null) {
            instance = new Singleton2();
            return instance;
        }
        return instance;
    }

}

// 如何是饿汉模式变得安全 ---》 加锁  --》 把不是原子的操作 --》 变成完整的
class Singleton3{

    // 并不是一开始就创建实例
    private static Singleton3 instance = null;

    private Singleton3(){
    }

    public static Singleton3 getInstance(){
        //使用到时候才 new 一个实例
        synchronized (Singleton3.class){ // 指定类对象 作为锁对象 ---》 类对象在JVM中之只有一个 所以 多线程下可以保证在一个对象上加锁
            if(instance==null) {
                instance = new Singleton3();
                return instance;
            }
        }
        /* 优化
         public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
        }
        return instance;
        }
         */
        return instance;
    }

}

public class ThreadSafe_SingletonPattern {

    public static void main(String[] args) {

        Singleton singleton= Singleton.getInstance();

        //Singleton singleton1=new Singleton(); 不能 new

        Singleton2 singleton2 = Singleton2.getInstance();

    }

}
