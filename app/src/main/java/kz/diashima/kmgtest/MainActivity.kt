package kz.diashima.kmgtest

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kz.diashima.kmgtest.adapters.NotesAdapter
import kz.diashima.kmgtest.databinding.ActivityMainBinding
import kz.diashima.kmgtest.db.AppDatabase
import kz.diashima.kmgtest.db.entity.Note


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), NotesAdapter.OnItemClickListener {

    private var settingsButton = false
    private var secondSwitch = 0
    private var formId = 1
    private var holeId = 1
    private lateinit var clickList: List<Note>
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pieChart = findViewById(R.id.pieChart)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        if (checkIfFirstEnter()) {
            Log.d("Test", "First enter")
            db.producerDao().insertNotes(Variables.setData())
            firstEnter()
        }


        initPieChart()
        //setDataToPieChart(95f, 5f)
        setNotesRecycler(db, holeId)
        setFirstData()

        binding.imageButton.setOnClickListener {
            if (!settingsButton) {
                binding.filterLayout.visibility = View.VISIBLE
                binding.groupView.visibility = View.GONE
                settingsButton = true
                binding.imageButton.setImageResource(R.drawable.ic_vector_close)
            } else {
                binding.filterLayout.visibility = View.GONE
                binding.groupView.visibility = View.VISIBLE
                settingsButton = false
                binding.imageButton.setImageResource(R.drawable.ic_vector_open)
            }
        }

        val firstSpinnerAdapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            Variables.firstSpinnerArray)

        val secondSpinnerMAdapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            Variables.secondSpinnerM)

        val secondSpinnerOAdapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            Variables.secondSpinnerO)

        val zhetybaiSpinnerAdapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            Variables.zhetybaiSpinner)

        val kalamkasSpinnerAdapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            Variables.kalamkasSpinner)

        val ozenSpinnerAdapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            Variables.ozenmunaigasSpinner)

        binding.firstSpinner.adapter = firstSpinnerAdapter

        binding.firstSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    secondSwitch = 0
                    binding.secondSpinner.adapter = secondSpinnerMAdapter
                } else if (p2 == 1) {
                    secondSwitch = 1
                    binding.secondSpinner.adapter = secondSpinnerOAdapter
                }
                binding.secondSpinner.isClickable = true
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.secondSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (secondSwitch == 0) {
                    //выделено ММГ
                    if (p2 == 0) {
                        binding.thirdSpinner.adapter = zhetybaiSpinnerAdapter
                        holeId = 1
                        setNotesRecycler(db, 1)
                    } else if (p2 == 1) {
                        binding.thirdSpinner.adapter = kalamkasSpinnerAdapter
                        holeId = 2
                        setNotesRecycler(db, 2)
                    }
                } else if (secondSwitch == 1) {
                    //выделено ОМГ
                    if (p2 == 0) {
                        binding.thirdSpinner.adapter = ozenSpinnerAdapter
                        holeId = 3
                        setNotesRecycler(db, 3)
                    }
                }
                binding.thirdSpinner.isClickable = true
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.textForm1.setOnClickListener {
            formId = 1
            setNotesRecycler(db, holeId)
            binding.textForm1.setBackgroundColor(Color.BLACK)
            binding.textForm2.setBackgroundColor(Color.parseColor("#31356099"))
            binding.textForm3.setBackgroundColor(Color.parseColor("#31356099"))
        }
        binding.textForm2.setOnClickListener {
            formId = 2
            setNotesRecycler(db, holeId)
            binding.textForm2.setBackgroundColor(Color.BLACK)
            binding.textForm1.setBackgroundColor(Color.parseColor("#31356099"))
            binding.textForm3.setBackgroundColor(Color.parseColor("#31356099"))
        }
        binding.textForm3.setOnClickListener {
            formId = 3
            setNotesRecycler(db, holeId)
            binding.textForm3.setBackgroundColor(Color.BLACK)
            binding.textForm2.setBackgroundColor(Color.parseColor("#31356099"))
            binding.textForm1.setBackgroundColor(Color.parseColor("#31356099"))
        }

        binding.swipeLayout.setOnRefreshListener {
            setNotesRecycler(db, holeId)
        }
    }

    private fun initPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.text = ""
        //hollow pie chart
        pieChart.isDrawHoleEnabled = false
        pieChart.setTouchEnabled(false)
        pieChart.setDrawEntryLabels(false)
        //adding padding
        pieChart.setExtraOffsets(20f, 0f, 20f, 20f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true
    }

    private fun setDataToPieChart(montly: Float, daily: Float = 0f) {
        Log.d("Test", "$montly and $daily")
        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(montly, "Остальное"))
        dataEntries.add(PieEntry(daily, "За сутки"))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#EF5350"))
        colors.add(Color.parseColor("#4CAF50"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(10f)
        //pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        //create hole in center
        pieChart.holeRadius = 65f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.parseColor("#272953"))

        pieChart.invalidate()
    }

    private fun setNotesRecycler(db: AppDatabase, boreholeId: Int) {
        val list = db.producerDao().getNotes(boreholeId)
        clickList = list
        Log.d("Test", list.toString())

        binding.recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = NotesAdapter(list, this@MainActivity, context, formId, secondSwitch, db)
        }
        binding.swipeLayout.isRefreshing = false
    }

    private fun setFirstData() {
        var sum = 0f
        for (note in clickList) {
            sum += note.oilExtraction
        }
        setDataToPieChart(sum)
    }

    private fun firstEnter() {
        val sharedPref = getSharedPreferences(Variables.save, Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean(Variables.save, false)
            apply()
        }
    }

    private fun checkIfFirstEnter() : Boolean {
        val sharedPreferences = getSharedPreferences(Variables.save, Context.MODE_PRIVATE)!!
        return sharedPreferences.getBoolean(Variables.save, true)
    }

    override fun onItemCLick(position: Int) {
        var sum = 0f
        for (note in clickList) {
            sum += note.oilExtraction
        }
        val daily = clickList[position].oilExtraction.toFloat()
        sum -= daily
        setDataToPieChart(sum, daily)
    }
}