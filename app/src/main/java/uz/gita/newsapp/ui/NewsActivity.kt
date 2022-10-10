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

    }
}