# ☕ PUNPUN Cafe Ordering System

## 📌 เกี่ยวกับโปรเจกต์
PUNPUN Cafe Ordering System เป็นแอปเดสก์ท็อป JavaFX สำหรับสั่งเครื่องดื่มและขนมในร้านกาแฟ โดยมีฟีเจอร์ทั้งระบบล็อกอิน, การสั่งเมนู, คำนวณราคา, และแสดงรายการสั่งซื้อ พร้อมระบบแอดมิน (Owner Dashboard) สำหรับดูสรุปยอดขาย

โปรเจกต์นี้สร้างขึ้นเพื่อเป็นตัวอย่างการใช้งาน Java, JavaFX, และแนวทางการเขียนโปรแกรมเชิงวัตถุ (OOP)

---

## 🛠 เทคโนโลยีหลักที่ใช้

* **Java 21**
* **JavaFX 21** (UI)
* **Maven** (จัดการโปรเจกต์)
* **JSON** (เก็บข้อมูลสมาชิกและออร์เดอร์)

---

## ⚙️ ฟีเจอร์หลัก

* ระบบล็อกอินสำหรับสมาชิก
* ระบบเลือกเมนูและจำนวน
* รองรับเมนูเครื่องดื่มและขนม (ปรับน้ำตาล/ไซส์เฉพาะเครื่องดื่ม)
* คำนวณราคาย่อยและราคารวมอัตโนมัติ
* บันทึกออร์เดอร์เป็นไฟล์ JSON (orders.json)
* หน้า Owner Dashboard ดูยอดขายรวมและรายการออร์เดอร์

---

## 📂 โครงสร้างโปรเจกต์ (สำคัญ)

```
/demo
  ├── pom.xml
  ├── members.json          # ข้อมูลสมาชิกสำหรับล็อกอิน
  ├── orders.json           # เก็บออร์เดอร์ที่ทำ
  └── src
      ├── main
      │   ├── java
      │   │   └── com.example
      │   │       ├── App.java         # จุดเริ่มต้นของแอป
      │   │       ├── auth
      │   │       │   ├── LoginPage.java
      │   │       │   └── RegisterPage.java
      │   │       ├── dashboard
      │   │       │   └── OwnerDashboard.java
      │   │       ├── database
      │   │       │   └── JSONDatabase.java
      │   │       ├── model          # โมเดลข้อมูล (Menu, Order, Member, ฯลฯ)
      │   │       ├── service        # ตรรกะธุรกิจ (PointSystem, Size)
      │   │       └── util           # เครื่องมือช่วย (Receipt)
      │   └── resources
      │       └── style.css
      └── test
          └── java
              └── com.example
                  └── AppTest.java
```

---

## ▶️ วิธีรันโปรเจกต์ (บนเครื่อง Windows / macOS / Linux)

1) เปิดเทอร์มินัล แล้วไปที่โฟลเดอร์ `demo`

```bash
cd demo
```

2) รันแอปด้วย Maven (ต้องติดตั้ง JDK 21 และ Maven ไว้ก่อน)

```bash
mvn clean javafx:run
```

> 💡 หากใช้ IDE เช่น IntelliJ IDEA ให้เปิดโฟลเดอร์ `demo` เป็นโปรเจกต์ Maven แล้วรัน `com.example.App` ได้เลย

---

## ✅ การทดสอบ (Unit Tests)

มีโครงสร้างสำหรับทดสอบด้วย JUnit 5 อยู่ที่ `demo/src/test/java/com/example/AppTest.java`

รันด้วยคำสั่ง:

```bash
mvn test
```

---

## 📝 ไฟล์ข้อมูลสำคัญ

* `demo/members.json` — รายการสมาชิกที่ใช้ล็อกอิน (username/password)
* `demo/orders.json` — เก็บข้อมูลออร์เดอร์เมื่อคลิก "Add Order"

---

## 📌 ขยายต่อได้อย่างไร (ตัวอย่าง)

* เพิ่มฟีเจอร์แก้ไข-ลบออร์เดอร์ได้
* เพิ่มการเลือก Topping หรือเครื่องดื่มพิเศษ
* เชื่อมต่อฐานข้อมูลจริง (SQLite/MySQL)
* เพิ่มระบบสมาชิกแบบลงทะเบียนพร้อมบันทึกลงไฟล์/DB