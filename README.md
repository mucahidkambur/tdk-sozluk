<p align="center">
  <a href="https://github.com/mucahitkambur/tdk-sozluk">
    <img src="resimler/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">TDK Sözlük 1.0.0</h3>

  <p align="center">
    TDK Sözlük uygulaması, TDK'nın verilerini kullanarak güncel Android teknolojileri ile geliştirilen açık kaynak bir sözlük uygulamasıdır.
    <br />


## İçerik

* [Proje Hakkında](#proje-hakkinda)
  * [Kullanılan Teknolojiler](#kullanilan-teknolojiler)
  * [Veriler](#veriler)
  * [Proje Detayı](#proje-detayi)
* [Katkıda Bulunmak](#katkida-bulunmak)
* [Lisans](#lisans)
* [İletişim](#iletisim)

## Proje Hakkında

[![Product Name Screen Shot][product-screenshot]]()

Projeyi geliştirme fikri, TDK'nın yeni web sitesinde verileri json olarak işlediğini farkettiğimde başladı. Gerek Play Store'da güzel bir sözlük uygulamasının olmaması gerekse de kendimi geliştirmek amaçlı bu projeyi geliştirmeye başladım. Elimden geldiğince Android caimasında kullanılan son teknolojiler ile uygulamayı geliştirip, kendini geliştirmek isteyenlere de güzel bir kaynak olması açısından açık kaynak olarak paylaştım. 


#### Kullanılan Teknolojiler
Uygulamayı tamamen Kotlin dili ile geliştirdim.
* Mimari: [MVVM](https://developer.android.com/topic/libraries/architecture)
* Network Kütüphanesi: [Retrofit](https://square.github.io/retrofit/)
* Dependency Injection Kütüphanesi: [Dagger](https://github.com/google/dagger)
* Veritabanı Kütüphanesi: [Room](https://developer.android.com/jetpack/androidx/releases/room)
* UI Kütüphanesi: [Data Binding](https://developer.android.com/topic/libraries/data-binding)

#### Veriler
Veriler için [sozluk.gov.tr](http://sozluk.gov.tr) adresi kullanıldı.

* Ana Sayfa İçeriği
```sh
http://sozluk.gov.tr/icerik
```

* Arama İçeriği
```sh
http://sozluk.gov.tr/gts?ara=
```
* Kelimeler Listesi
```sh
http://sozluk.gov.tr/autocomplete.json
```
#### Proje Detayları
* Kelime tahminlerini kullanabilmek için Splash ekranında tek seferlik olarak "Kelimeler Listesi" adresinden kelimeleri çekip bunları Room ile veritabanına kaydediyoruz.
* Main Fragment'ta icerik kısmındaki verileri alıp işleyip ekranda gösteriyoruz.
* Search Fragment'ta bir search kütüphanesi kullanarak aratılan kelimeyi Room ve LiveData yardımı ile Recycler View'da gösteriyoruz. Her yapılan arama sonrasında yine Room ile aratılan kelimeleyi veritabanında table_history'e ekliyoruz.
* Search Detail Fragment'ta "Arama İçeriği" adresine kelimeyi parametre olarak gönderip dönen verileri işleyerek Recycler View'da gösteriyoruz.

### Katkıda Bulunmak
Açık kaynağın en güzel yanlarından biri isteyen herkesin projeye destek sağlayabilmesi. Projeye katkı sağlamak, hata çözmek istiyorsanız aşağıdaki adımı izleyebilirsiniz.

1- Projeyi forkla </br>
2- Yaptıklarını commit'le </br>
3- Branch'e pushla </br>
4- Pull Request talebi aç

### Lisans

> Copyright (C) 2019 Mücahit KAMBUR

Bu program özgür yazılımdır: Özgür Yazılım Vakfı tarafından yayımlanan GNU
Genel Kamu Lisansı’nın sürüm 3 ya da (isteğinize bağlı olarak) daha sonraki
sürümlerinin hükümleri altında yeniden dağıtabilir ve/veya değiştirebilirsiniz.

Bu program, yararlı olması umuduyla dağıtılmış olup, programın BİR TEMİNATI
YOKTUR; TİCARETİNİN YAPILABİLİRLİĞİNE VE ÖZEL BİR AMAÇ İÇİN UYGUNLUĞUNA dair
bir teminat da vermez. Ayrıntılar için GNU Genel Kamu Lisansı’na göz atınız.

Bu programla birlikte GNU Genel Kamu Lisansı’nın bir kopyasını elde etmiş
olmanız gerekir. Eğer elinize ulaşmadıysa <http://www.gnu.org/licenses/>
adresine bakınız.

### İletişim

Mail - mucahit-kambur@hotmail.com </br>
Portfolio: [https://mucahitkambur.github.io](https://mucahitkambur.github.io) </br>
Twitter: [@mucahitkambur](https://twitter.com/mucahitkambur) </br>
LinkedIn: [mucahitkambur](https://www.linkedin.com/in/mucahitkambur/)

[product-screenshot]: resimler/tanitim.jpg