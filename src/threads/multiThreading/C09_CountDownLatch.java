package threads.multiThreading;

import java.util.concurrent.CountDownLatch;

/*
bazı threadlerin main thread ve diğer threadlerden önce çalışmasını ve işini
tamamladıktan sonra diğer threadlerin kaldığı yerden devam etmesini istediğimizde
CountDownLatch classının metodları ile öncelik vermek istediğimiz
threadlerin(worker threads) sayısı kadar bir sayaç başlatıp sayaç 0 olana kadar
diğer threadler bekletilebilir.

Bir veya birden fazla thread'in belirli bir görev setinin tamamlanmasını beklemesini
 sağlamak için kullanılır. Genellikle, belirli sayıda alt görevin tamamlanmasını beklemek
 için kullanılır ve tüm görevler tamamlandıktan sonra ana thread’in devam etmesini sağlar.

 oluşturdugumuz threadlerin icerisinden bazı threadler main thread ve diger olusturdugumuz threadlerden
önceliğe sahip olabiliyor işini tamamladıkta sonra diger threadlerimiz calısmaya devam eder
CountDownLatch methodlar ile oncelik vererek istegimiz threadlerin sayısı kadar
bir sayac baslatıp sayac 0 olaran kadar diger threadlerimizi bekletiriz
 */

public class C09_CountDownLatch {
    public static void main(String[] args) {

        System.out.println("burada main thread başladı...main thread işe karıstı ");

        CountDownLatch latch=new CountDownLatch(3);
        //öncelik vermek istediğimiz thread sayısı ile sayaç oluşturuyoruz

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Öğrencinin bilgileri alınıyor...");
                System.out.println("Öğrencinin numarası üretiliyor..");
                //hemzemin geçit
                try {
                    latch.await();//thread1, worker threadler işini bitirene kadar , sayaç 0 olana kadar bekler
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Öğrenci kaydediliyor.....");
            }
        });
        thread1.start();

        System.out.println("main thread devam ediyor...son satır ");

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();//thread2, workerları bekler
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Öğrencinin düzeyine göre sorular hazırlanıyor...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread2.start();

        //seviye belirlemek için 3 tane thread
        WorkerThreads worker1=new WorkerThreads("writing",6000,latch);
        WorkerThreads worker2=new WorkerThreads("reading",5000,latch);
        WorkerThreads worker3=new WorkerThreads("speaking",3000,latch);
        worker1.start();
        worker2.start();
        worker3.start();
        //workerlar kendi aralarında asenkron:yine yarış halinde

        try {
            latch.await();
//            worker1.join();
//            worker2.join();
//            worker3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread devam ediyor...son satır");






    }
}
class WorkerThreads extends Thread{

    public int duration;

    public CountDownLatch latch;

    //paramli const
    public WorkerThreads(String name, int duration, CountDownLatch latch) {
        super(name);//new Thread(String name)
        this.duration = duration;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" çalışmaya başladı....");
        System.out.println("Seviye tespiti yapılıyor.....");
        try {
            Thread.sleep(this.duration);//thıs demesek sadece duratıon yazsakda ıslemı yapardı
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" seviye tespitini tamamladı...");
        latch.countDown();//sayacı bir azaltır
    }
}
