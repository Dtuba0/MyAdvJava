package threads;
//1. yol : thread classını extend ederek kendı yolumuzu olusturabılırız
public class C02_MyThread extends Thread{

    @Override
    public void run(){
        //threade yaptırmak ıstedıgımız işler (kodları) run methodu ıcerısıne yazdıracagız

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Çalışan thread : "+Thread.currentThread().getName());
        System.out.println("MyThread threadı calısıyor : HARİKA \uD83E\uDD20");
    }


}
