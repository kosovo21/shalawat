package com.example.shalawat.data.local

/**
 * Provides default shalawat entries to seed the database on first launch.
 * Each entry references a pre-bundled audio file in assets/audio/.
 */
object DatabaseSeeder {

    suspend fun seed(dao: ShalawatDao) {
        dao.insertAll(*defaultShalawatEntries.toTypedArray())
    }

    private val defaultShalawatEntries = listOf(
        ShalawatEntity(
            title = "Shalawat Ibrahimiyah",
            arabicText = "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ كَمَا صَلَّيْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيمَ إِنَّكَ حَمِيدٌ مَجِيدٌ، اللَّهُمَّ بَارِكْ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ كَمَا بَارَكْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيمَ إِنَّكَ حَمِيدٌ مَجِيدٌ",
            transliteration = "Allaahumma shalli 'alaa Muhammadin wa 'alaa aali Muhammadin kamaa shallaita 'alaa Ibraahiima wa 'alaa aali Ibraahiima innaka hamiidum majiid. Allaahumma baarik 'alaa Muhammadin wa 'alaa aali Muhammadin kamaa baarakta 'alaa Ibraahiima wa 'alaa aali Ibraahiima innaka hamiidum majiid.",
            translation = "Ya Allah, limpahkanlah shalawat kepada Nabi Muhammad dan keluarga Nabi Muhammad, sebagaimana Engkau telah melimpahkan shalawat kepada Nabi Ibrahim dan keluarga Nabi Ibrahim. Sesungguhnya Engkau Maha Terpuji lagi Maha Mulia. Ya Allah, limpahkanlah keberkahan kepada Nabi Muhammad dan keluarga Nabi Muhammad, sebagaimana Engkau telah melimpahkan keberkahan kepada Nabi Ibrahim dan keluarga Nabi Ibrahim. Sesungguhnya Engkau Maha Terpuji lagi Maha Mulia.",
            audioFileName = "shalawat_1.mp3",
            audioSource = "asset",
            category = "daily",
            virtues = "This is the most well-known shalawat, recited in every prayer (tasyahhud). It is considered the most authentic form of sending blessings upon the Prophet, as it was taught by the Prophet Muhammad ﷺ himself."
        ),
        ShalawatEntity(
            title = "Shalawat Nariyah",
            arabicText = "اللَّهُمَّ صَلِّ صَلَاةً كَامِلَةً وَسَلِّمْ سَلَامًا تَامًّا عَلَى سَيِّدِنَا مُحَمَّدٍ الَّذِي تَنْحَلُّ بِهِ الْعُقَدُ وَتَنْفَرِجُ بِهِ الْكُرَبُ وَتُقْضَى بِهِ الْحَوَائِجُ وَتُنَالُ بِهِ الرَّغَائِبُ وَحُسْنُ الْخَوَاتِمِ وَيُسْتَسْقَى الْغَمَامُ بِوَجْهِهِ الْكَرِيمِ وَعَلَى آلِهِ وَصَحْبِهِ فِي كُلِّ لَمْحَةٍ وَنَفَسٍ بِعَدَدِ كُلِّ مَعْلُومٍ لَكَ",
            transliteration = "Allaahumma shalli shalaatan kaamilatan wa sallim salaaman taamman 'alaa sayyidinaa Muhammadini-lladzii tanhallu bihil 'uqadu wa tanfariju bihil kurabu wa tuqdhaa bihil hawaa-iju wa tunaalu bihir raghaa-ibu wa husnul khawaatimi wa yustasqal ghamaamu bi wajhihil kariimi wa 'alaa aalihii wa shahbihii fii kulli lamhatin wa nafasin bi 'adadi kulli ma'luumin lak.",
            translation = "Ya Allah, limpahkanlah shalawat yang sempurna dan salam yang lengkap kepada junjungan kami Nabi Muhammad SAW, yang dengan beliau terurai segala ikatan, terlepas segala kesusahan, terpenuhi segala hajat, tercapai segala keinginan dan husnul khatimah, serta dimintakan hujan dengan wajahnya yang mulia, dan kepada keluarga dan sahabatnya dalam setiap kedipan mata dan hembusan napas sebanyak bilangan setiap yang diketahui oleh-Mu.",
            audioFileName = "shalawat_2.mp3",
            audioSource = "asset",
            category = "popular",
            virtues = "Known as the \"Fire Prayer\" (Nariyah) for its speed in producing results. Traditionally recited for the relief of difficulties and the fulfillment of urgent needs."
        ),
        ShalawatEntity(
            title = "Shalawat Tibbil Qulub",
            arabicText = "اللَّهُمَّ صَلِّ عَلَى سَيِّدِنَا مُحَمَّدٍ طِبِّ الْقُلُوبِ وَدَوَائِهَا وَعَافِيَةِ الْأَبْدَانِ وَشِفَائِهَا وَنُورِ الْأَبْصَارِ وَضِيَائِهَا وَعَلَى آلِهِ وَصَحْبِهِ وَسَلِّمْ",
            transliteration = "Allaahumma shalli 'alaa sayyidinaa Muhammadin thibbil quluubi wa dawaa-ihaa wa 'aafiyatil abdaani wa syifaa-ihaa wa nuuril abshaari wa dhiyaa-ihaa wa 'alaa aalihii wa shahbihii wa sallim.",
            translation = "Ya Allah, limpahkanlah shalawat kepada junjungan kami Nabi Muhammad, obat hati dan penyembuhnya, kesehatan badan dan penyehatnya, cahaya penglihatan dan sinarnya, dan kepada keluarga serta sahabatnya, dan limpahkanlah salam.",
            audioFileName = "shalawat_3.mp3",
            audioSource = "asset",
            category = "healing",
            virtues = "Recited for healing of the heart, body, and soul. The name 'Tibbil Qulub' means 'Medicine of the Hearts,' and it is traditionally used as a spiritual remedy for physical and emotional ailments."
        ),
        ShalawatEntity(
            title = "Shalawat Badar",
            arabicText = "صَلَاةُ اللهِ عَلَى مُحَمَّدٍ صَلَاةُ اللهِ عَلَيْهِ وَسَلَّمَ، صَلَاةً تَفُوقُ كُلَّ صَلَاةٍ كَمَا يَلِيقُ بِحَضْرَتِهِ وَجَمَالِهِ وَكَمَالِهِ",
            transliteration = "Shalaatullaahi 'alaa Muhammad, shalaatullaahi 'alaihi wa sallam. Shalaatan tafuuqu kulla shalaatin kamaa yaliiqu bi hadhratihii wa jamaalihii wa kamaalihii.",
            translation = "Semoga Allah melimpahkan shalawat kepada Nabi Muhammad, shalawat Allah kepadanya dan salam. Shalawat yang melebihi segala shalawat, sebagaimana layak bagi kemuliaan, keindahan, dan kesempurnaan beliau.",
            audioFileName = "shalawat_4.mp3",
            audioSource = "asset",
            category = "daily",
            virtues = "Named after the Battle of Badr, this shalawat is often recited in gatherings and celebrations. It is believed to bring blessings and protection, commemorating the victory granted by Allah."
        ),
        ShalawatEntity(
            title = "Shalawat Munjiyat",
            arabicText = "اللَّهُمَّ صَلِّ عَلَى سَيِّدِنَا مُحَمَّدٍ صَلَاةً تُنْجِينَا بِهَا مِنْ جَمِيعِ الْأَهْوَالِ وَالْآفَاتِ وَتَقْضِي لَنَا بِهَا جَمِيعَ الْحَاجَاتِ وَتُطَهِّرُنَا بِهَا مِنْ جَمِيعِ السَّيِّئَاتِ وَتَرْفَعُنَا بِهَا عِنْدَكَ أَعْلَى الدَّرَجَاتِ وَتُبَلِّغُنَا بِهَا أَقْصَى الْغَايَاتِ مِنْ جَمِيعِ الْخَيْرَاتِ فِي الْحَيَاةِ وَبَعْدَ الْمَمَاتِ",
            transliteration = "Allaahumma shalli 'alaa sayyidinaa Muhammadin shalaatan tunjiinaa bihaa min jamii'il ahwaali wal aafaat, wa taqdhii lanaa bihaa jamii'al haajaat, wa tuthahhirunaa bihaa min jamii'is sayyi-aat, wa tarfa'unaa bihaa 'indaka a'lad darajaat, wa tuballighunaa bihaa aqshal ghaayaat min jamii'il khairaati fil hayaati wa ba'dal mamaat.",
            translation = "Ya Allah, limpahkanlah shalawat kepada junjungan kami Nabi Muhammad, shalawat yang dengannya Engkau selamatkan kami dari segala ketakutan dan bencana, Engkau penuhi segala hajat kami, Engkau bersihkan kami dari segala keburukan, Engkau angkat kami ke derajat yang tertinggi di sisi-Mu, dan Engkau sampaikan kami kepada segala tujuan dari segala kebaikan dalam kehidupan dan setelah kematian.",
            audioFileName = "shalawat_5.mp3",
            audioSource = "asset",
            category = "protection",
            virtues = "The 'Saving Prayer' (Munjiyat) is recited for protection from all calamities, purification from sins, and elevation in spiritual rank. It is one of the most comprehensive shalawat for seeking safety in this life and the hereafter."
        )
    )
}
