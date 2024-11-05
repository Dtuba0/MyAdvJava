package generics.bounding;
//generic yapılarda parametre olarak kullanılan data tipi üstten sınırlandırılabilir.
//T data tipindeki field ile Matematiksel hesaplama yapacağız.
//T:Number classı ve alt sınıflarıyla üstten sınırlandıralım
//T:BYTE,SHORT,INTEGER,DOUBLE,LONG,FLOAT, NUMBER
//T : her şey olabilirdi istersek sınırlandırabiliriz
//T sadece sayısal degerler olabilir
//Integer,Long,Double,Byte,Float,Short -> Number childidir
//T : data tipi matematiksel işlemler yapılıcagi zaman number'a üstten sınırlandırma yapılabilir
//generic yapılarda parametre olarak kullanılan data tipi üstten sınırlandırılabilir
public class GenericUpperBound<T extends Number> {//T:String,User,Object bunlar olamaz
    public T[] numberArray;//field

    //numberArrayin içindeki elemanların ortalamasını bulan bir metod
    public Double countAverage(){

        double sum=0;

        for (T number:this.numberArray){

            sum+=number.doubleValue();//elemanların double data tipine dönüştürülmesini sağlar.
        }

        double avg=sum/this.numberArray.length;

        return avg;
    }

}
