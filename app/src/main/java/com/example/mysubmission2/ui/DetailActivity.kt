package com.example.mysubmission2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mysubmission2.R
import com.example.mysubmission2.adapter.SectionPagerAdapter
import com.example.mysubmission2.databinding.ActivityDetailBinding
import com.example.mysubmission2.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"

        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.followers,
                R.string.following,
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)


        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if (username != null) {
            viewModel.setUserDetail(username)
            showLoading(true)
        }
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                showLoading(false)
                binding.apply {
                    tvNameDetail.text = it.name ?: "-"
                    tvUsernameDetail.text = it.login
                    tvCompany.text = it.company ?: "-"
                    tvLocation.text = it.location ?: "-"
                    tvFollowers.text = it.followers.toString()
                    tvRepository.text = it.public_repos.toString()
                    tvFollowing.text = it.following.toString()
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .into(imgAvatarDetail)
                }
            }
        })

        var izChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.toggleFav.isChecked = true
                        izChecked = true
                    } else {
                        binding.toggleFav.isChecked = false
                        izChecked = false
                    }
                }
            }
        }

        binding.apply {
            toggleFav.setOnClickListener {
                izChecked = !izChecked
                if (izChecked) {
                    username?.let {
                        if (avatarUrl != null) {
                            viewModel.addToFavorite(it, id, avatarUrl)
                        }
                    }
                    Toast.makeText(this@DetailActivity, R.string.add_fav, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.removeFromFavorite(id)
                    Toast.makeText(this@DetailActivity, R.string.remove_fav, Toast.LENGTH_SHORT).show()
                }
                binding.toggleFav.isChecked = izChecked
            }


            buttonProfileBack.setOnClickListener {
                onBackPressed()
            }
        }

        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}