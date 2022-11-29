package com.edts.sampleapps.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.edts.sampleapps.presentation.home.model.Event
import com.edts.sampleapps.presentation.home.model.GridItem


val menuMocks by lazy {
    arrayOf(
        GridItem(1, icon = Icons.Filled.NoteAdd, "Buat Event"),
        GridItem(2, icon = Icons.Filled.LocalActivity, "Event Saya"),
        GridItem(3, icon = Icons.Filled.Person, "Daftar Karyawan"),
        GridItem(4, icon = Icons.Filled.Assignment, "Reservasi Meeting"),
        GridItem(5, icon = Icons.Filled.BusinessCenter, "Pengajuan Cuti"),
        GridItem(6, icon = Icons.Filled.Alarm, "Absensi")
    )
}

val eventMocks by lazy {
    arrayOf(
        Event(
            "https://dm0qx8t0i9gc9.cloudfront.net/thumbnails/video/BgrICs-NZj4hksnn3/videoblocks-from-out-of-focus-to-in-focus-close-up-footage-of-businesswoman-hands-typing-fast-at-the-laptop-computer-on-her-desk-in-the-office_hlkjlikpf_thumbnail-1080_01.png",
            "JUL",
            "13",
            "Android Training Day",
            "Elevenia F30",
            "Conference Room",
        ),
        Event(
            "https://images.unsplash.com/photo-1593642634315-48f5414c3ad9?ixid=MXwxMjA3fDF8MHxzZWFyY2h8MXx8b2ZmaWNlfGVufDB8fDB8&ixlib=rb-1.2.1&w=1000&q=80",
            "JUL",
            "13",
            "Android Training Day",
            "Elevenia F30",
            "Conference Room"
        ),
        Event(
            "https://dm0qx8t0i9gc9.cloudfront.net/thumbnails/video/Vd3bj2jPe/videoblocks-close-up-of-business-woman-working-with-laptop-computer-in-office-office-worker-looking-at-business-presentation-on-laptop-screen-woman-analyzing-finance-report-on-notebook-screen_hw8as_0lm_thumbnail-1080_01.png",
            "JUL",
            "13",
            "Android Training Day",
            "Elevenia F30",
            "Conference Room"
        ),
        Event(
            "https://ak.picdn.net/shutterstock/videos/12549533/thumb/1.jpg",
            "JUL",
            "13",
            "Android Training Day",
            "Elevenia F30",
            "Conference Room"
        ),
        Event(
            "https://blueprint.theblueground.com/wp-content/uploads/2018/12/blueprint-blueground-coworking-space-chicago-second-shift.jpg",
            "JUL",
            "13",
            "Android Training Day",
            "Elevenia F30",
            "Conference Room"
        ),
        Event(
            "https://www.futuristarchitecture.com/wp-content/uploads/2019/02/WORKKI-Coworking-Space-1.jpg",
            "JUL",
            "13",
            "Android Training Day",
            "Elevenia F30",
            "Conference Room"
        ),
    )
}