package com.example.leadership.model

import java.net.URI

val RegionParticipants = listOf(
    Participant(
        name = Name("Matthew"),
        avatar = URI("https://s3-eu-west-1.amazonaws.com/blog-ecotree/blog/0001/01/ad46dbb447cd0e9a6aeecd64cc2bd332b0cbcb79.jpeg"),
        score = 1847U,
        previousScore = 1847U
    ),
    Participant(
        name = Name("Jacob"),
        avatar = URI("https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80"),
        score = 3056U,
        previousScore = 3056U
    ),
    Participant(
        name = Name("Ruben"),
        avatar = URI("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPCXISA7AWonO3J24GKCgtJ9e4OTuaJHSBM7rcN3j28GfR6eJAJTe1Gi_AlJpG6wuFnCs&usqp=CAU"),
        score = 1674U,
        previousScore = 1674U
    ),
    Participant(
        name = Name("Clifford"),
        avatar = URI("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8DyJg8-snfm4DIPa782s_qz2rlTqUb-_BQQ&usqp=CAU"),
        score = 1134U,
        previousScore = 1130U
    ),
    Participant(
        name = Name("Tara"),
        avatar = URI("https://easydrawingguides.com/wp-content/uploads/2021/11/how-to-draw-mario-pixel-art-featured-image-1200-811x1024.png"),
        score = 845U,
        previousScore = 850U
    ),
    Participant(
        name = Name("Pascal"),
        avatar = URI("https://static.wikia.nocookie.net/meme/images/1/1b/Pop_catt.jpg/revision/latest/smart/width/250/height/250?cb=20210722143809"),
        score = 760U,
        previousScore = 750U
    ),
    Participant(
        name = Name("Quincy"),
        avatar = URI("https://e0.pxfuel.com/wallpapers/881/123/desktop-wallpaper-meme-cat-bongo-cat-meme-thumbnail.jpg"),
        score = 734U,
        previousScore = 660U
    ),
    Participant(
        name = Name("Oliver"),
        avatar = URI("https://featherflagnation.com/wp-content/uploads/3x5-Proof-Crying-Cat-v1.jpg"),
        score = 544U,
        previousScore = 600U
    ),
    Participant(
        name = Name("Yuri"),
        avatar = URI("https://cdn.shopify.com/s/files/1/0311/0875/9684/products/stickersaledacat_1024x.png?v=1660839929"),
        score = 541U,
        previousScore = 123U
    ),
    Participant(
        name = Name("John"),
        avatar = URI("https://forklog.com/wp-content/uploads/dogecoin-min.webp"),
        score = 764U,
        previousScore = 600U
    )
)
val NationalParticipants = listOf(
    Participant(
        name = Name("Matthew"),
        avatar = URI("https://www.thesprucepets.com/thmb/hxWjs7evF2hP1Fb1c1HAvRi_Rw0=/2765x0/filters:no_upscale():strip_icc()/chinese-dog-breeds-4797219-hero-2a1e9c5ed2c54d00aef75b05c5db399c.jpg"),
        score = 4353U,
        previousScore = 1847U
    ),
    Participant(
        name = Name("Jacob"),
        avatar = URI("https://hips.hearstapps.com/hmg-prod/images/small-fuffy-dog-breeds-1623362663.jpg?crop=1.00xw:0.753xh;0,0.0719xh&resize=1200:*"),
        score = 3056U,
        previousScore = 3056U
    ),
    Participant(
        name = Name("Ruben"),
        avatar = URI("https://publish.purewow.net/wp-content/uploads/sites/2/2021/06/smallest-dog-breeds-toy-poodle.jpg?fit=728%2C524"),
        score = 2342U,
        previousScore = 1674U
    ),
    Participant(
        name = Name("Clifford"),
        avatar = URI("https://images.unsplash.com/photo-1610878180933-123728745d22?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FuYWRhJTIwbmF0dXJlfGVufDB8fDB8fHww&w=1000&q=80"),
        score = 1134U,
        previousScore = 1130U
    ),
    Participant(
        name = Name("Tara"),
        avatar = URI("https://img.freepik.com/free-photo/green-field-tree-blue-skygreat-as-backgroundweb-banner-generative-ai_1258-158251.jpg?w=2000"),
        score = 845U,
        previousScore = 850U
    ),
    Participant(
        name = Name("Pascal"),
        avatar = URI("https://blog.depositphotos.com/wp-content/uploads/2017/07/Soothing-nature-backgrounds-2-1024x684.jpg"),
        score = 760U,
        previousScore = 342U
    ),
)

val GlobalParticipants = RegionParticipants.take(2)

val GlobalAppState = AppState(
    regionLeaderboard = Leaderboard.fromParticipants(RegionParticipants),
    nationalLeaderboard = Leaderboard.fromParticipants(NationalParticipants),
    globalLeaderboard = Leaderboard.fromParticipants(GlobalParticipants),
)