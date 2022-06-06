package kz.diashima.kmgtest

import kz.diashima.kmgtest.db.entity.Note

object Variables {

    val firstSpinnerArray = arrayOf("ММГ", "ОМГ")
    val secondSpinnerM = arrayOf("Жетыбай", "Каламкас")
    val secondSpinnerO = arrayOf("ОзенМунайГаз")
    val zhetybaiSpinner = arrayOf("ZHT_0001(Жетыбай)")
    val kalamkasSpinner = arrayOf("KLM_0001(Каламкас)")
    val ozenmunaigasSpinner = arrayOf("OZN_0001(ОзенМунайГаз)")
    val save = "save"

    fun setData() : MutableList<Note> {
        var testList: MutableList<Note> = ArrayList()
        testList.add(
            Note(
            boreholeId = 1,
            date = "01.05.2022",
            name = "ZHT_0001",
            oilExtraction = 100,
            liquidExtraction =  200,
            waterCut = "1000",
            ndin = "50",
            gasDebit = "52"
            )
        )
        testList.add(
                Note(
                    boreholeId = 1,
                    date = "02.05.2022",
                    name = "ZHT_0001",
                    oilExtraction = 200,
                    liquidExtraction =  300,
                    waterCut = "300",
                    ndin = "5",
                    gasDebit = "5"
                )
        )
        testList.add(
            Note(
                boreholeId = 1,
                date = "03.05.2022",
                name = "ZHT_0001",
                oilExtraction = 150,
                liquidExtraction =  250,
                waterCut = "200",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 1,
                date = "04.05.2022",
                name = "ZHT_0001",
                oilExtraction = 250,
                liquidExtraction =  50,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 1,
                date = "05.05.2022",
                name = "ZHT_0001",
                oilExtraction = 100,
                liquidExtraction =  200,
                waterCut = "250",
                ndin = "1",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 1,
                date = "06.05.2022",
                name = "ZHT_0001",
                oilExtraction = 700,
                liquidExtraction =  200,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 1,
                date = "07.05.2022",
                name = "ZHT_0001",
                oilExtraction = 200,
                liquidExtraction =  200,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 1,
                date = "08.05.2022",
                name = "ZHT_0001",
                oilExtraction = 300,
                liquidExtraction =  100,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 2,
                date = "01.05.2022",
                name = "KLM_0001",
                oilExtraction = 500,
                liquidExtraction =  100,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 2,
                date = "02.05.2022",
                name = "KLM_0001",
                oilExtraction = 300,
                liquidExtraction =  100,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 2,
                date = "03.05.2022",
                name = "KLM_0001",
                oilExtraction = 200,
                liquidExtraction =  100,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "01.05.2022",
                name = "OZN_0001",
                oilExtraction = 200,
                liquidExtraction =  100,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "02.05.2022",
                name = "OZN_0001",
                oilExtraction = 150,
                liquidExtraction =  500,
                waterCut = "2",
                ndin = "1",
                gasDebit = "2"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "03.05.2022",
                name = "OZN_0001",
                oilExtraction = 300,
                liquidExtraction =  100,
                waterCut = "3",
                ndin = "7",
                gasDebit = "1"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "04.05.2022",
                name = "OZN_0001",
                oilExtraction = 250,
                liquidExtraction =  400,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "05.05.2022",
                name = "OZN_0001",
                oilExtraction = 500,
                liquidExtraction =  200,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "06.05.2022",
                name = "OZN_0001",
                oilExtraction = 1000,
                liquidExtraction =  400,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "07.05.2022",
                name = "OZN_0001",
                oilExtraction = 350,
                liquidExtraction =  100,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "08.05.2022",
                name = "OZN_0001",
                oilExtraction = 450,
                liquidExtraction =  200,
                waterCut = "500",
                ndin = "1",
                gasDebit = "5"
            )
        )
        testList.add(
            Note(
                boreholeId = 3,
                date = "09.05.2022",
                name = "OZN_0001",
                oilExtraction = 200,
                liquidExtraction =  100,
                waterCut = "300",
                ndin = "5",
                gasDebit = "5"
            )
        )
        return testList
    }
}