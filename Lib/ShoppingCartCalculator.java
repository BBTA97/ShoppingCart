import java.util.ArrayList;

public class ShoppingCartCalculator {
    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - ส่งค่ากลับเป็น 0.0 เมื่อsku เป็น null,empty
     * - ส่งค่ากลับเป็น0.0 เมื่อ price,quantityติดลบ พร้อมขึ้นข้อความเตือน
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1) = เมื่อมีสินค้าที่ sku='BOGO' อยู่จะเข้าโปรโมชัน(ซื้อ2จ่าย1,3จ่าย2,4จ่าย2)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%) = เมื่อ sku='BULK' แล้ว quantity ของสินค้ารวมกันแล้ว มากกว่าหรือเท่ากับ 6 จะนำราคารวมของสินค้านั้นมาลด10%
     * @param ค่าที่รับคือ บาร์โค้ดรหัสสินค้า(เพื่อตรวจสอบว่าสินค้านั้นๆมีโปรโมชันใดๆหรือไม่),ชื่อสินค้า,ราคาสินค้า,จำนวนสินค้าที่ซื้อ 
     * โดยจะแทยด้วยตัวแปล(String sku, String name, double price, int quantit) ตามลำดับ
     * @return ราคารวมทั้งหมดหลังผ่านการคำนวณโปรโมชัน
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        // TODO: เขียนโค้ด Implementation ที่นี่
        
        double pay1=0,pay2=0;

        if( items == null || items.isEmpty())
            return 0.0;

        for(CartItem item : items){

            if(item.sku().equals("NORMAL")){//คำนวณราคาปกติ
                if(item.name().equals("Bread"))
                    pay1 += item.price() * item.quantity();
                else if(item.name().equals("Milk"))
                    pay2 += item.price() * item.quantity();
            }

            if(item.sku().equals("BOGO")){//คำนวณราคาซื้อ1แถม1
                if(item.name().equals("Bread")){
                    if(item.quantity()%2 == 0)
                        pay1 += item.price() * (item.quantity()/2);
                    else if(item.quantity()%2 == 1)
                        pay1 += item.price() *(item.quantity()/2 + item.quantity()%2);}
                else if(item.name().equals("Milk")){
                    if(item.quantity()%2 == 0)
                        pay2 += item.price() * (item.quantity()/2);
                    else if(item.quantity()%2 == 1)
                        pay2 += item.price() *(item.quantity()/2 + item.quantity()%2);}
            }

            if(item.sku().equals("BULK")){//คำนวณราคาซื้อ >= 6 ชิ้น ลด 10%
                if(item.name().equals("Bread")){
                    pay1 += item.price() * item.quantity();
                    if(item.quantity() >= 6)
                        pay1 = pay1*0.9;}
                else if(item.name().equals("Milk")){
                    pay2 += item.price() * item.quantity();
                    if(item.quantity() >= 6)
                        pay2 = pay2*0.9;}}
         
                if(item.price() < 0){
                    return 0.0;
                }
                else if(item.quantity() < 0){
                    return 0.0;
                }
        }        
        return pay1+pay2;
    }
}

/*import java.util.ArrayList; 
 
public class ShoppingCartCalculator { 
 
    /** 
     * เป็นเมธอดตรวจสอบสินค้า(sku)เพื่อนำโปรโมชั่นมาคำนวณราคาสินค้าโดยใช้price,quantity 
     * - ส่งค่ากลับ0.0บาทเมื่อ sku= null หรือ empty 
     * - คำนวณราคาสินค้าปกติเมื่อsku="NORMAL" 
     * - คำนวณราคาสินค้า(ซื้อ2จ่าย1,ซื้อ3จ่าย2,ซื้อ4จ่าย2)เมื่อsku="BOGO"  
     * - คำนวณราคาสินค้า(ซื้อ >= 6 ชิ้น ลด 10%)เมื่อsku="BULK"  
     * -@param items ประกอบด้วย(sku,name,price,quantity) 
     * sku คือ string ของโค้ดสินค้าที่ใช้ตรวจจำแนกการคำนวณราคาสินค้าตามโปรโมชั่น 
     * name คือ ชื่อของสินค้า(Bread,Milk) priceคือราคาของสินค้า{Bread=25บาท,Milk=15บาท} 
     * quantity คือจำนวนสินค้าที่ซื้อ 
     * @return ราคารวมทั้งหมดหลังคำนวณโปรโมชั่น (หน่วย: บาท) 
     */ 
    /*public static double calculateTotalPrice(ArrayList<CartItem> items) { 
        // TODO: เขียนโค้ด Implementation ที่นี่ 
         
        double count1=0,count2=0; 
        if( items==null|| items.isEmpty() ) 
        {return 0.0; } 
        else for (CartItem item : items) { 
        if (item.sku().equals("NORMAL") ){ //คำนวณปกติ 
            if(item.name().equals("Bread")  ) { 
            count1 += item.price() * item.quantity(); } 
            else if(item.name().equals("Milk")) 
            {count2 += item.price() * item.quantity();}} 
 
        if (item.sku().equals("BOGO") ) //ลดราคาโดยการแถม 
        {if(item.name().equals("Bread")  ) { 
            count1 += item.price() *(Math.ceil(item.quantity()/2.0)); } 
            else if(item.name().equals("Milk")) 
            {count2+=item.price() *(Math.ceil(item.quantity()/2.0));} 
        } 
        if (item.sku().equals("BULK") ) //ลดราคา10% 
        {    
           if(item.name().equals("Bread")  ) { 
           count1 += item.price() * item.quantity(); 
            if (item.quantity()>=6) { 
          count1-=count1*10/100;} 
        } 
           else if(item.name().equals("Milk")) 
           {count2+=item.price() * item.quantity(); 
           if (item.quantity()>=6 ) { 
            count2-=count2*10/100; } 
          }  } } 
            return count1+count2;      
    }
} */