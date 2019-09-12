<p align="center">
  <a href="https://github.com/mucahitkambur/tdk-sozluk">
    <img src="resimler/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">TDK Sözlük 1.0.2</h3>

  <p align="center">
    TDK Sözlük uygulaması, TDK'nın verilerini kullanarak güncel Android teknolojileri ile geliştirilen açık kaynak bir sözlük uygulamasıdır.
    <br />
    </p>
</p>

<p align="center">
    <a href='https://play.google.com/store/apps/details?id=com.mucahitkambur.tdksozluk'>
        <img src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height=90px/>
    </a>
</p>    


## İçerik

* [Proje Hakkında](#proje-hakkında)
  * [Motivasyon](#motivasyon) 
  * [Kullanılan Teknolojiler](#kullanılan-teknolojiler)
  * [Veriler](#veriler)
  * [Proje Detayları](#proje-detayları)
* [Katkıda Bulunmak](#katkıda-bulunmak)
* [Lisans](#lisans)
* [İletişim](#iletişim)

## Proje Hakkında

[![Product Name Screen Shot][product-screenshot]]()

#### Motivasyon
Projeyi geliştirme fikri, TDK'nın yeni web sitesinde verileri json olarak işlediğini farkettiğimde başladı. Gerek Play Store'da güzel bir sözlük uygulamasının olmaması gerekse de kendimi geliştirmek amaçlı bu projeyi geliştirmeye başladım. Elimden geldiğince Android caimasında kullanılan son teknolojiler ile uygulamayı geliştirip, kendini geliştirmek isteyenlere de güzel bir kaynak olması açısından açık kaynak olarak paylaştım. 


#### Kullanılan Teknolojiler
Uygulamayı tamamen Kotlin dili ile geliştirdim.

* Mimari
    * [MVVM][0]
    * [Data Binding][1]
    * [Lifecycle][2] 
    * [LiveData][3]
    * [Navigation][4]
    * [Room][5]
    * [ViewModel][6]

* 3.Parti Kütüphaneler
    * [Dagger 2][7]
    * [Retrofit 2][8]
    * [OkHttp 3][9]
    * [GSON][10]

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
* Main Fragment'ta "Ana Sayfa İçeriği" kısmındaki verileri alıp işleyip ekranda gösteriyoruz.
* Search Fragment'ta bir search kütüphanesi kullanarak aratılan kelimeyi Room ve LiveData yardımı ile Recycler View'da gösteriyoruz. Her yapılan arama sonrasında yine Room ile aratılan kelimeleyi veritabanında table_history'e ekliyoruz.
* Search Detail Fragment'ta "Arama İçeriği" adresine kelimeyi parametre olarak gönderip dönen verileri işleyerek Recycler View'da gösteriyoruz.

### Katkıda Bulunmak
Açık kaynağın en güzel yanlarından biri isteyen herkesin projeye destek sağlayabilmesi. Projeye katkı sağlamak, hata çözmek istiyorsanız aşağıdaki adımı izleyebilirsiniz.

1. Projeyi forkla
2. Yaptıklarını commit'le
3. Branch'e pushla
4. Pull Request talebi aç

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

Mail - mucahit-kambur@hotmail.com

Portfolio: [https://mucahitkambur.github.io](https://mucahitkambur.github.io)

Twitter: [@mucahitkambur](https://twitter.com/mucahitkambur)

LinkedIn: [mucahitkambur](https://www.linkedin.com/in/mucahitkambur/)

Blog: [mucahitkambur.wordpress.com](https://mucahitkambur.wordpress.com)

[product-screenshot]: resimler/tanitim.jpg

[0]: https://developer.android.com/jetpack/arch/
[1]: https://developer.android.com/topic/libraries/data-binding/
[2]: https://developer.android.com/topic/libraries/architecture/lifecycle
[3]: https://developer.android.com/topic/libraries/architecture/livedata
[4]: https://developer.android.com/topic/libraries/architecture/navigation/
[5]: https://developer.android.com/topic/libraries/architecture/room
[6]: https://developer.android.com/topic/libraries/architecture/viewmodel
[7]: https://dagger.dev/users-guide
[8]: https://square.github.io/retrofit/
[9]: https://square.github.io/okhttp/
[10]: https://github.com/google/gson