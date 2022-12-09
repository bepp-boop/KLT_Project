package com.example.klt_project.ui.aboutUs

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klt_project.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    var videoUrl = "https://kltlogistik.se/content/uploads/20/12/startv3.mp4"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutUsViewModel =
            ViewModelProvider(this).get(AboutUsViewModel::class.java)

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val videoView: VideoView = binding.videoView
        val uri: Uri = Uri.parse(videoUrl)
        videoView.setVideoURI(uri)
        //val mediaController = MediaController(context)
        //mediaController.setAnchorView(videoView)
        //mediaController.setMediaPlayer(videoView)
        //videoView.setMediaController(mediaController)
        videoView.start()

        aboutUsViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}