package uz.gita.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_news.*
import uz.gita.newsapp.R
import uz.gita.newsapp.database.ArticleDataBase
import uz.gita.newsapp.repository.NewsRepository
import uz.gita.newsapp.ui.fragments.BreakingNewsFragment
import uz.gita.newsapp.ui.fragments.SavedNewsFragment
import uz.gita.newsapp.ui.fragments.SearchNewsFragment
import uz.gita.newsapp.ui.viewmodel.NewsViewModel
import uz.gita.newsapp.ui.viewmodel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {
    var viewModel: NewsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDataBase(this))
        val newsViewModelProviderFactory =
            NewsViewModelProviderFactory(application = application, newsRepository)
        viewModel = ViewModelProvider(this, newsViewModelProviderFactory)[NewsViewModel::class.java]
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.breakingNewsFragment -> supportFragmentManager.beginTransaction()
                    .replace(R.id.newsNavHostFragment, BreakingNewsFragment()).commit()
                R.id.searchNewsFragment -> supportFragmentManager.beginTransaction()
                    .replace(R.id.newsNavHostFragment, SearchNewsFragment()).commit()
                R.id.savedNewsFragment -> supportFragmentManager.beginTransaction()
                    .replace(R.id.newsNavHostFragment, SavedNewsFragment()).commit()
            }
            return@setOnItemSelectedListener true
        }

    }
}