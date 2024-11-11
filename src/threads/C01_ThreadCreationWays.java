package threads;

public class C01_ThreadCreationWays {

    public static void main(String[] args) throws InterruptedException {

        //Her java programında default olarak 1 tane thread baslatılır : main thread
        //Thread.currentThread().getName()); mevcut threadın ısmını getırır
        System.out.println("Mevcut thread : "+Thread.currentThread().getName());//Mevcut thread : main


        //javada thread olusturmanın ıkı yolu vardır
        //run methodu override etmelıyız
        //1. yol
        Thread thread1=new C02_MyThread();
        thread1.start();//threadı baslatır ve run metodunu cagırır
        //thread1.run(); yenı bır thread baslatmaz olan thread ıle devam eder metod cagırıyoruz yanı 
        thread1.setName("threadcik \uD83E\uDD73");

        //2. yol
        Runnable runnable=new C03_MyRunnable();//inheritance
        Thread thread2 =new Thread(runnable);
        thread2.start();
        thread2.setName("canımthreadcimm \uD83D\uDE0E");

        //2. yol : anonymous class. isimsiz sınıf
        //runnable içinde sadece 1 tane implemente edılecek metod var
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                //threadın işlemleri
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("calısan thread : "+Thread.currentThread().getName());
                System.out.println("Anonim class ile üretilen thread başladı...");
            }
        });
        thread3.start();
        thread3.setName("süperthread\uD83D\uDE1C");

        //2. yol daha da kolay bır yonteme sahıp functıonal ınterface : lambda exp ile
        Thread thread4 = new Thread(()->{
            //run metopdunun bodysındeyız :)
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("calısan thread : "+Thread.currentThread().getName());
            System.out.println("Lambda exp  ile üretilen thread başladı...");

        });
        thread4.start();
        thread4.setName("çakmathread\uD83D\uDE07");

        //main threadı bıraz bekletebılırız : uyutalım
        try {
            Thread.sleep(1000);//hangi threadın gorevlerı arasında cagrılırsa o threadı bekletır
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        try {
            thread1.join();//hangi threadin kodları içinde çağrılırsa bu threadi bekletir:thread1 işini bitirene kadar
            thread2.join();//bekletir:thread2 işini bitirene kadar
            thread3.join();//bekletir:thread3 işini bitirene kadar
            thread4.join();//bekletir:thread4 işini bitirene kadar
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }//main thread artık devam edebılır


        System.out.println("main metod burada tamamlandı .");//main thread görevi
        //NOT:her thread kendi işini(run metodu içindeki kodları) senkron(sıralı)
        //threadler kendi arasında ASENKRON çalışır.

    }
}

