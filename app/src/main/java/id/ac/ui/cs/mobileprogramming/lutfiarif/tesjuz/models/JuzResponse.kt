package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

data class JuzResponse(
    val code: Int,
    val `data`: Data,
    val status: String
) {
    data class Data(
        val ayahs: List<Ayah>,
        val edition: Edition,
        val number: Int,
        val surahs: Surahs
    ) {
        data class Ayah(
            val hizbQuarter: Int,
            val juz: Int,
            val manzil: Int,
            val number: Int,
            val numberInSurah: Int,
            val page: Int,
            val ruku: Int,
            val sajda: Any,
            val surah: Surah,
            val text: String
        ) {
            data class Surah(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )
        }

        data class Edition(
            val direction: String,
            val englishName: String,
            val format: String,
            val identifier: String,
            val language: String,
            val name: String,
            val type: String
        )

        data class Surahs(
            val `100`: X100,
            val `101`: X101,
            val `102`: X102,
            val `103`: X103,
            val `104`: X104,
            val `105`: X105,
            val `106`: X106,
            val `107`: X107,
            val `108`: X108,
            val `109`: X109,
            val `110`: X110,
            val `111`: X111,
            val `112`: X112,
            val `113`: X113,
            val `114`: X114,
            val `78`: X78,
            val `79`: X79,
            val `80`: X80,
            val `81`: X81,
            val `82`: X82,
            val `83`: X83,
            val `84`: X84,
            val `85`: X85,
            val `86`: X86,
            val `87`: X87,
            val `88`: X88,
            val `89`: X89,
            val `90`: X90,
            val `91`: X91,
            val `92`: X92,
            val `93`: X93,
            val `94`: X94,
            val `95`: X95,
            val `96`: X96,
            val `97`: X97,
            val `98`: X98,
            val `99`: X99
        ) {
            data class X100(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X101(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X102(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X103(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X104(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X105(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X106(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X107(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X108(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X109(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X110(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X111(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X112(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X113(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X114(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X78(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X79(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X80(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X81(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X82(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X83(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X84(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X85(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X86(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X87(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X88(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X89(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X90(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X91(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X92(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X93(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X94(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X95(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X96(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X97(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X98(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )

            data class X99(
                val englishName: String,
                val englishNameTranslation: String,
                val name: String,
                val number: Int,
                val numberOfAyahs: Int,
                val revelationType: String
            )
        }
    }
}