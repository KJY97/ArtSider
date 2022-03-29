<template>
<div>
<h1 class='header'>NETFLIX</h1>
<!-- 카테고리 별 추천 리스트 -->
<h1> 카테고리 별 추천</h1>
  <div class='wrapper'>
    <section id='category-section1'>
      <v-btn
        class="arrow-btn left-arrow"
      >‹</v-btn>
      <!-- <a href="#category-section3" class="arrow__btn left-arrow">‹</a> -->
      <!-- 제가 이거 버튼으로 바꿔놓겠습니다. -->
      <!-- 버튼으로 변경하면  -->
      <!-- 추가 작업도 -->
      <!-- <a href="#category-section3" class="arrow__btn left-arrow" @click="()=> {return false}">‹</a> -->
      <!-- <a href="#category-section3" class="arrow__btn left-arrow" return false;>‹</a> -->
      <div class='item'>
        <a href='#'>
          <img
            src='https://w.namu.la/s/1837eead0acfe94c64358c7662f75cc5af8a71765f05e2ff9f2ee8558ef463f51cac6f7b60f9d5b11461a754629d5c70c944a241ce5bf5d0b3bbb884fb06674db0e1a93c30a5f5b808cd2ba4f39943fb'
            alt="Describe Image">

            <h1 class="heading">Heading One</h1>
            <p class="duration">Duration: 10 min</p>
        </a>
      </div>
      <div class="item">
        <a href="#">
        <img
          src="https://ww.namu.la/s/8502a417c1a70b093761804de67f8f1c1c816946f7f674a01307e53297d996d265ffbc84ed5d16321158d4c2231025bd14f2c93c8284533b16ec5d4b840d12a35d92f846a7a1d6b6d633d2f6f388603c"
          alt="Describe Image">
          
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
          
        </a>
      </div>
      <div class="item">
        
        <a href="#">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABQCoK53qihwVPLRxPEDX98nyYpGbxgi5cc0ZOM4iHQu7KQvtgNyaNM5PsgI0vy5g3rLPZdjGCFr1EggrCPXpL77p2H08jV0tNEmIfs_e8KUfvBJ6Ay5nM4UM1dl-58xA6t1swmautOM.webp"
          alt="Describe Image">
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
        </a>
      </div>
      <div class="item">
        <a href="#">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABdYtAqj8CyaJTWq5taD8Ro_UgwH3nne9QpFGVps-2J3IG-leqrfqXFii4jzZn48nPYTkrlwKQEV0R7_cEKlYBPRzdKqNODc-Oz26IL3LlLgFboXibIWXwxzeYxzuqn0I9TpARjeByw.jpg"
          alt="Describe Image">
          
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
        </a>
      </div>
      <div class="item">
        <a href="#">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABbcCX42tsqGbBvO2y9CQv5-7QvYbCfoHtXsuc6NPCtZaKa4l4fBX3XWvUwG9F2A3CTsNpHVmulxBbdXKwK8Q6xGjejd9FoadGkZ7CnGrSl601TOQjzSHJ23NuIPC8j0QMGORL4uRIA.jpg"
          alt="Describe Image">
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
          </a>
      </div>
      <a href="#category-section2" class="arrow__btn right-arrow">›</a>
    </section>

    <section id="category-section2">
      <a href="#category-section1" class="arrow__btn left-arrow">‹</a>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABVopDZ5Fy9_fk_HO5WxHTXKKjKhtWFupbSjuvPwfLK_vytzon4EwRUdGgYJ34JwPxOTK_NkV3aXfkULMB0Dcct-FyfqzH-X44VXuRMp4QeBHlvKwWeZFpZlGdItPzmmg4scmwhG7SQ.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABTOj1-116yVcgKWMU2dI3GFR4x0fSkiGsqtLLeLUxRR7STaksjAqBTrYlTfrB8nIGnGvXksi0ewXAhVGg6-pLxpFOIfcpjK-pf8D5xehFZo5a6vJbo4L0AGbrzglbyUoq255QBJgRQ.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABd3IBDpxbRcHXvRMFCZeKa2aHLU1P4SJtrACMS9om3yhLjqPlvNlmR_fypPxjtbsbnKaC4JZhPSpDG4r_kdxSHHAltWguMcCB-1Y1OShr2zWfUv7Whf_39fNH5ZJ3_0gxQrs0akmQjQz44_LT7jXH5LMZ7iMBAzac5IEj4m7Fn_5OWEGYnVsDsKG-QTommDooULMDF9bEw.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABXSd7bhDddcwkq9XpksoQFCHVx29Sxl_h4hb2n3F2GIt32a4XWcOnctQfgnT5qdolv8UML6_xNB5CJ89h56wueb13mYmEBr0sx5e9iLPdtVcOQAOmKXKWHHXwFvJuCUwuNnL3s8eAQwqLXPVMHMEsujM684rUGrZNF2btN2GRy5-RyEslsxZO93V2Q_H2bWs8A8oayt1h5M.webp"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABbXWODpAWqVXcmmjMA7K-2mPkQpvwCLfSdeyhVXzR8A3MSpdSEnnjf4HEJJTYC-TnktU6njTUGAxmzWEYCaJbk4v_ZeL-7QGzmkvYBjg_N-evr2XmcX-Fanoyvu_nimFP4iigPe4O3Vr_WcgplhwkDrJwPUJa84wRLrNAx3TufN5V7cWRP4indqu5HQahvgFEqfL9zjp4g.jpg"
          alt="Describe Image">
      </div>
      <a href="#category-section3" class="arrow__btn right-arrow">›</a>
    </section>

    <section id="category-section3">
      <a href="#category-section2" class="arrow__btn left-arrow">‹</a>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABRr4YxdaABuAuH_3FmSQZn7BCvLp-UUPsXE9MiYpvFP3CSUHV2zOew5oVqKqqdaOd3tbFVS0Uf67uIs7_eZydlCghg4nK0nMatRpPImABwTOhnNzCLCxdKrua7pPIcPCZqBYTeAO5g.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABTyWK1MKaw8GcObtz47R2Tj7wkLJ7qQu9tk6TVpcoyxpzD4B-zZ569bQ5vGrREBL-MWFkGilXUwy7tCDaj2XOGkUB4RsbbFAmp9NgSr6lygMpUGNHSlyfrFbUORsRkrxSIoh_ggOvg.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABY7NwkWEJIfXsn6t3Li4bGSCQ1nEErPisI5ZZtXlC-_VRBZOUrhWK5X3vt3t6SR_cpgVhCwxgQqFFDJhk62Kk8hawOnYGZMr0LKeLczMFV2zalCFjkcdLksvT4HB2LEi6LFyruyk3Uu0LmNGsHfC2A8Bly60smr_3sDbz4RruXcklPOG1qYq9wUVu3zfaiwNvqmG4b8aFA.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABemXHOga9feFnOux6I2YyACBD94wvM7N3vcTGIfMpQ_BcaXeoeM9XyzdVdamKtxt0SHXZfvsl6czcp3E48tXMLtHBxuQsh1BjHtPGgJDZ81je_FjItINiqzLtir0A30s_e4KR8G3d7AYAPDjZVOY97bNpzNqtkcHcGp7fGnJByVCps1uLfG9U9tK3Ma1A_3JbRt0NiT2_Q.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABVxuRB932hvre-XP0gh6ar5ztoR3Oe3QjKHkyvcDnRak2MKXOrx5H7mFQSvggefMFOppwEs7ZCCpiqrJ_CYGvtvYB9NpU4SWUtNO6uV2u-DTID267AcHjHcGvGBQJ1ufddDkxcGOZyi5MlOQ5QUmBun4652FbYUnib3zMYQDgcna_Pvz8y_HO5fbokxezrRR1JZAAiqFSQ.jpg"
          alt="Describe Image">
      </div>
      <a href="#category-section1" class="arrow__btn right-arrow">›</a>
    </section>
  </div>
  <div>
  <h1> 나와 비슷한 사람</h1>
  <div class="wrapper">
    <section id="similar-section1">
      <a href="#similar-section3" class="arrow__btn left-arrow">‹</a>
      <div class="item">
            <h1 class="rank">1</h1>
        <a href="#">
          <v-img
          src="https://w.namu.la/s/1837eead0acfe94c64358c7662f75cc5af8a71765f05e2ff9f2ee8558ef463f51cac6f7b60f9d5b11461a754629d5c70c944a241ce5bf5d0b3bbb884fb06674db0e1a93c30a5f5b808cd2ba4f39943fb"
          >
          </v-img>
        </a>
      </div>
      <div class="item">
          <h1 class="rank">2</h1>          
        <a href="#">
        <v-img
          src="https://ww.namu.la/s/8502a417c1a70b093761804de67f8f1c1c816946f7f674a01307e53297d996d265ffbc84ed5d16321158d4c2231025bd14f2c93c8284533b16ec5d4b840d12a35d92f846a7a1d6b6d633d2f6f388603c"
          alt="Describe Image"
        >
        </v-img>
        </a>
      </div>
      <div class="item">
          <h1 class="rank">3</h1>
        <a href="#">
        <img
          src="https://i.pinimg.com/474x/fb/21/bf/fb21bff4095f918fdf2b771e36d8fed2.jpg"
          alt="Describe Image">
        </a>
      </div>
      <div class="item">
          <h1 class="rank">4</h1>
        <a href="#">
        <img
          src="https://newsimg.sedaily.com/2020/01/21/1YXQDTM7WM_1.jpg"
          alt="Describe Image">
        </a>
      </div>
      <div class="item">
          <h1 class="rank">5</h1>
        <a href="#">
        <img
          src="https://www.m-i.kr/news/photo/202101/784601_561474_542.jpg"
          alt="Describe Image">
          </a>
      </div>
      <a href="#similar-section2" class="arrow__btn right-arrow">›</a>
    </section>
    <section id="similar-section2">
      <a href="#similar-section1" class="arrow__btn left-arrow">‹</a>
       <div class="item">
        <h1 class="rank">6</h1>
        <a href="#">
        <img
          src="https://img1.yna.co.kr/etc/inner/KR/2020/01/02/AKR20200102075700005_02_i_P2.jpg"
          alt="Describe Image">
        </a>
      </div>
      <div class="item">
          <h1 class="rank">7</h1>
        <img
          src="https://image.ajunews.com/content/image/2018/08/03/20180803104751108116.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">8</h1>
        <img
          src="https://img.hankyung.com/photo/201906/2019061008373553224-540x763.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">9</h1>
        <img
          src="http://ojsfile.ohmynews.com/PHT_IMG_FILE/2021/0309/IE002770932_PHT.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">10</h1>
        <img
          src="https://img.hankyung.com/photo/202001/BF.21391966.1-1200x.jpg"
          alt="Describe Image">
      </div>
      <a href="#similar-section3" class="arrow__btn right-arrow">›</a>
    </section>
    <section id="similar-section3">
      <a href="#similar-section2" class="arrow__btn left-arrow">‹</a>
       <div class="item">
          <h1 class="rank">11</h1>
        <img
          src="https://hobbyen.co.kr/news/data/20210727/p1065599709407562_865_thum.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">12</h1>
        <img
          src="https://cdn.gukjenews.com/news/photo/202106/2235289_2227032_824.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">13</h1>
        <img
          src="https://pbs.twimg.com/media/FLxD2R0aIAQEam4?format=jpg&name=large"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">14</h1>
        <img
          src="https://image.yes24.com/themusical/upFiles/Themusical/Play/post_poster(s)22.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">15</h1>
        <img
          src="https://cdn.energykorea.co.kr/news/photo/202111/44265_36074_2612.jpg"
          alt="Describe Image">
      </div>
      <a href="#similar-section1" class="arrow__btn right-arrow">›</a>
    </section>
  </div>
  </div>
  <div>
  <h1> 태그 별</h1>
  <div class="wrapper">
    <section id="tag-section1">
      <a href="#tag-section3" class="arrow__btn left-arrow">‹</a>
      <div class="item">
        <a href="#">
          <img
            src="https://w.namu.la/s/1837eead0acfe94c64358c7662f75cc5af8a71765f05e2ff9f2ee8558ef463f51cac6f7b60f9d5b11461a754629d5c70c944a241ce5bf5d0b3bbb884fb06674db0e1a93c30a5f5b808cd2ba4f39943fb"
            alt="Describe Image">

            <h1 class="heading">Heading One</h1>
            <p class="duration">Duration: 10 min</p>
        </a>
      </div>
      <div class="item">

        <a href="#">
        <img
          src="https://ww.namu.la/s/8502a417c1a70b093761804de67f8f1c1c816946f7f674a01307e53297d996d265ffbc84ed5d16321158d4c2231025bd14f2c93c8284533b16ec5d4b840d12a35d92f846a7a1d6b6d633d2f6f388603c"
          alt="Describe Image">
          
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
          
        </a>
      </div>
      <div class="item">
        
        <a href="#">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABQCoK53qihwVPLRxPEDX98nyYpGbxgi5cc0ZOM4iHQu7KQvtgNyaNM5PsgI0vy5g3rLPZdjGCFr1EggrCPXpL77p2H08jV0tNEmIfs_e8KUfvBJ6Ay5nM4UM1dl-58xA6t1swmautOM.webp"
          alt="Describe Image">
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
        </a>
      </div>
      <div class="item">
        <a href="#">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABdYtAqj8CyaJTWq5taD8Ro_UgwH3nne9QpFGVps-2J3IG-leqrfqXFii4jzZn48nPYTkrlwKQEV0R7_cEKlYBPRzdKqNODc-Oz26IL3LlLgFboXibIWXwxzeYxzuqn0I9TpARjeByw.jpg"
          alt="Describe Image">
          
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
        </a>
      </div>
      <div class="item">
        <a href="#">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABbcCX42tsqGbBvO2y9CQv5-7QvYbCfoHtXsuc6NPCtZaKa4l4fBX3XWvUwG9F2A3CTsNpHVmulxBbdXKwK8Q6xGjejd9FoadGkZ7CnGrSl601TOQjzSHJ23NuIPC8j0QMGORL4uRIA.jpg"
          alt="Describe Image">
          <h1 class="heading">Heading One</h1>
          <p class="duration">Duration: 10 min</p>
          </a>
      </div>
      <a href="#tag-section2" class="arrow__btn right-arrow">›</a>
    </section>

    <section id="tag-section2">
      <a href="#tag-section1" class="arrow__btn left-arrow">‹</a>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABVopDZ5Fy9_fk_HO5WxHTXKKjKhtWFupbSjuvPwfLK_vytzon4EwRUdGgYJ34JwPxOTK_NkV3aXfkULMB0Dcct-FyfqzH-X44VXuRMp4QeBHlvKwWeZFpZlGdItPzmmg4scmwhG7SQ.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABTOj1-116yVcgKWMU2dI3GFR4x0fSkiGsqtLLeLUxRR7STaksjAqBTrYlTfrB8nIGnGvXksi0ewXAhVGg6-pLxpFOIfcpjK-pf8D5xehFZo5a6vJbo4L0AGbrzglbyUoq255QBJgRQ.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABd3IBDpxbRcHXvRMFCZeKa2aHLU1P4SJtrACMS9om3yhLjqPlvNlmR_fypPxjtbsbnKaC4JZhPSpDG4r_kdxSHHAltWguMcCB-1Y1OShr2zWfUv7Whf_39fNH5ZJ3_0gxQrs0akmQjQz44_LT7jXH5LMZ7iMBAzac5IEj4m7Fn_5OWEGYnVsDsKG-QTommDooULMDF9bEw.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABXSd7bhDddcwkq9XpksoQFCHVx29Sxl_h4hb2n3F2GIt32a4XWcOnctQfgnT5qdolv8UML6_xNB5CJ89h56wueb13mYmEBr0sx5e9iLPdtVcOQAOmKXKWHHXwFvJuCUwuNnL3s8eAQwqLXPVMHMEsujM684rUGrZNF2btN2GRy5-RyEslsxZO93V2Q_H2bWs8A8oayt1h5M.webp"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABbXWODpAWqVXcmmjMA7K-2mPkQpvwCLfSdeyhVXzR8A3MSpdSEnnjf4HEJJTYC-TnktU6njTUGAxmzWEYCaJbk4v_ZeL-7QGzmkvYBjg_N-evr2XmcX-Fanoyvu_nimFP4iigPe4O3Vr_WcgplhwkDrJwPUJa84wRLrNAx3TufN5V7cWRP4indqu5HQahvgFEqfL9zjp4g.jpg"
          alt="Describe Image">
      </div>
      <a href="#tag-section3" class="arrow__btn right-arrow">›</a>
    </section>

    <section id="tag-section3">
      <a href="#tag-section2" class="arrow__btn left-arrow">‹</a>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABRr4YxdaABuAuH_3FmSQZn7BCvLp-UUPsXE9MiYpvFP3CSUHV2zOew5oVqKqqdaOd3tbFVS0Uf67uIs7_eZydlCghg4nK0nMatRpPImABwTOhnNzCLCxdKrua7pPIcPCZqBYTeAO5g.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/412e4119fb212e3ca9f1add558e2e7fed42f8fb4/AAAABTyWK1MKaw8GcObtz47R2Tj7wkLJ7qQu9tk6TVpcoyxpzD4B-zZ569bQ5vGrREBL-MWFkGilXUwy7tCDaj2XOGkUB4RsbbFAmp9NgSr6lygMpUGNHSlyfrFbUORsRkrxSIoh_ggOvg.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABY7NwkWEJIfXsn6t3Li4bGSCQ1nEErPisI5ZZtXlC-_VRBZOUrhWK5X3vt3t6SR_cpgVhCwxgQqFFDJhk62Kk8hawOnYGZMr0LKeLczMFV2zalCFjkcdLksvT4HB2LEi6LFyruyk3Uu0LmNGsHfC2A8Bly60smr_3sDbz4RruXcklPOG1qYq9wUVu3zfaiwNvqmG4b8aFA.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABemXHOga9feFnOux6I2YyACBD94wvM7N3vcTGIfMpQ_BcaXeoeM9XyzdVdamKtxt0SHXZfvsl6czcp3E48tXMLtHBxuQsh1BjHtPGgJDZ81je_FjItINiqzLtir0A30s_e4KR8G3d7AYAPDjZVOY97bNpzNqtkcHcGp7fGnJByVCps1uLfG9U9tK3Ma1A_3JbRt0NiT2_Q.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
        <img
          src="https://occ-0-243-299.1.nflxso.net/dnm/api/v5/rendition/a76057bcfd003711a76fb3985b1f2cf74beee3b8/AAAABVxuRB932hvre-XP0gh6ar5ztoR3Oe3QjKHkyvcDnRak2MKXOrx5H7mFQSvggefMFOppwEs7ZCCpiqrJ_CYGvtvYB9NpU4SWUtNO6uV2u-DTID267AcHjHcGvGBQJ1ufddDkxcGOZyi5MlOQ5QUmBun4652FbYUnib3zMYQDgcna_Pvz8y_HO5fbokxezrRR1JZAAiqFSQ.jpg"
          alt="Describe Image">
      </div>
      <a href="#tag-section1" class="arrow__btn right-arrow">›</a>
    </section>
  </div>
  </div>
  <div>
  <h1> 공연 순위</h1>
  <div class="wrapper">
    <section id="rank-section1">
      <a href="#rank-section3" class="arrow__btn left-arrow">‹</a>
      <div class="item">
          <h1 class="rank">1</h1>
        <a href="#">
          <img
          src="https://w.namu.la/s/1837eead0acfe94c64358c7662f75cc5af8a71765f05e2ff9f2ee8558ef463f51cac6f7b60f9d5b11461a754629d5c70c944a241ce5bf5d0b3bbb884fb06674db0e1a93c30a5f5b808cd2ba4f39943fb"
          >
          <!-- <img
            src="https://w.namu.la/s/1837eead0acfe94c64358c7662f75cc5af8a71765f05e2ff9f2ee8558ef463f51cac6f7b60f9d5b11461a754629d5c70c944a241ce5bf5d0b3bbb884fb06674db0e1a93c30a5f5b808cd2ba4f39943fb"
            alt="Describe Image"> -->
        </a>
      </div>
      <div class="item">
          <h1 class="rank">2</h1>          
        <a href="#">
        <img
          src="https://ww.namu.la/s/8502a417c1a70b093761804de67f8f1c1c816946f7f674a01307e53297d996d265ffbc84ed5d16321158d4c2231025bd14f2c93c8284533b16ec5d4b840d12a35d92f846a7a1d6b6d633d2f6f388603c"
          alt="Describe Image">
        </a>
      </div>
      <div class="item">
          <h1 class="rank">3</h1>
        <a href="#">
        <img
          src="https://i.pinimg.com/474x/fb/21/bf/fb21bff4095f918fdf2b771e36d8fed2.jpg"
          alt="Describe Image">
        </a>
      </div>
      <div class="item">
          <h1 class="rank">4</h1>
        <a href="#">
        <img
          src="https://newsimg.sedaily.com/2020/01/21/1YXQDTM7WM_1.jpg"
          alt="Describe Image">
        </a>
      </div>
      <div class="item">
          <h1 class="rank">5</h1>
        <a href="#">
        <img
          src="https://www.m-i.kr/news/photo/202101/784601_561474_542.jpg"
          alt="Describe Image">
          </a>
      </div>
      <a href="#rank-section2" class="arrow__btn right-arrow">›</a>
    </section>

    <section id="rank-section2">
      <a href="#rank-section1" class="arrow__btn left-arrow">‹</a>
      <div class="item">
        <h1 class="rank">6</h1>
        <a href="#">
        <img
          src="https://img1.yna.co.kr/etc/inner/KR/2020/01/02/AKR20200102075700005_02_i_P2.jpg"
          alt="Describe Image">
        </a>
      </div>
      <div class="item">
          <h1 class="rank">7</h1>
        <img
          src="https://image.ajunews.com/content/image/2018/08/03/20180803104751108116.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">8</h1>
        <img
          src="https://img.hankyung.com/photo/201906/2019061008373553224-540x763.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">9</h1>
        <img
          src="http://ojsfile.ohmynews.com/PHT_IMG_FILE/2021/0309/IE002770932_PHT.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">10</h1>
        <img
          src="https://img.hankyung.com/photo/202001/BF.21391966.1-1200x.jpg"
          alt="Describe Image">
      </div>
      <a href="#rank-section3" class="arrow__btn right-arrow">›</a>
    </section>
    <section id="rank-section3">
      <a href="#rank-section2" class="arrow__btn left-arrow">‹</a>
      <div class="item">
          <h1 class="rank">11</h1>
        <img
          src="https://hobbyen.co.kr/news/data/20210727/p1065599709407562_865_thum.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">12</h1>
        <img
          src="https://cdn.gukjenews.com/news/photo/202106/2235289_2227032_824.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <p class="rank">13</p>
        <img
          src="https://pbs.twimg.com/media/FLxD2R0aIAQEam4?format=jpg&name=large"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">14</h1>
        <img
          src="https://image.yes24.com/themusical/upFiles/Themusical/Play/post_poster(s)22.jpg"
          alt="Describe Image">
      </div>
      <div class="item">
          <h1 class="rank">15</h1>
        <img
          src="https://cdn.energykorea.co.kr/news/photo/202111/44265_36074_2612.jpg"
          alt="Describe Image">
      </div>
      <a href="#rank-section1" class="arrow__btn right-arrow">›</a>
    </section>
  </div>
</div>
    <h1>스와이퍼</h1> 
  <div class="swiper">
    <div class="swiper-wrapper">
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
      <div class="swiper-slide">Resize me!</div>
    </div>
    <div class="swiper-button-next"></div>
    <div class="swiper-button-prev"></div>
  </div>

</div>
</template>

<script>

  export default {
    name: 'Main',

    components: {
    },
    // data () {
    //   return {
    //     items: [
    //       {
    //         src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
    //       },
    //       {
    //         src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
    //       },
    //       {
    //         src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
    //       },
    //       {
    //         src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
    //       },
    //     ],
    //   }
    // },
  }

  
</script>
<style lang="scss" scoped>


$itemGrow: 1.2;
$duration: 250ms;

html {
  scroll-behavior: smooth;
}

body {
  margin: 0;
  background-color: #000;
  font-family: Arial;
}

img {
  max-height: 350px;
  // max-width: 300px;
}

.header {
  color: red;
  text-align: center;
}

.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: xxx-large;
  // border: solid black 1px; 
  // margin-bottom: 50px;
}

.wrapper {
  display: grid;
  grid-template-columns: repeat(3,100%);
  overflow:hidden;
  scroll-behavior: smooth;

  section {
    width: 100%;
    position: relative;
    display: grid;
    grid-template-columns: repeat(5, auto);
    margin: 20px 0;
    margin-bottom: 10rem;
    
    .item {
      position: relative;
      padding: 0 2px;
      transition: $duration all;
      max-width: 250px;
      max-height: 70px;
      
      &:hover { 
        margin: 0 40px;
        transform: scale(1.2);
      }


      .heading {
        position: absolute;
        bottom: 20px;
        left: 20px;
        color: #fff;
      }

      .duration {
        position: absolute;
        bottom: 0;
        left: 20px;
        color: #fff;
      }
      
    }

    .arrow-btn {
      position: absolute;
      color: #ff0000;
      text-decoration: none;
      font-size: 6em;
      background:rgb(0,0,0);
      width: 80px;
      padding: 20px;
      text-align: center;
      z-index: 1;
      // border: solid black 1px;
    }

    .left-arrow {
      top:0; bottom:0; left:0;
      background: 0%;
      // background: linear-gradient(-90deg, rgba(0,0,0,0) 0%, rgba(0,0,0,1) 100%);
    }
    
    .right-arrow {
      top:0; bottom:0; right: 0;
      background: 0%;
      // background: linear-gradient(90deg, rgba(0,0,0,0) 0%, rgba(0,0,0,1) 100%);
    }

  }
}

 // .category-section {
  //   width: 100%;
  //   position: relative;
  //   display: grid;
  //   grid-template-columns: repeat(5, auto);
  //   margin: 20px 0;
  // }

  // .similar-section {
  //   width: 100%;
  //   position: relative;
  //   display: grid;
  //   grid-template-columns: repeat(5, auto);
  //   margin: 20px 0;
  // }

  // .tag-section {
  //   width: 100%;
  //   position: relative;
  //   display: grid;
  //   grid-template-columns: repeat(5, auto);
  //   margin: 20px 0;
  // }
  
  // .rank-section {
  //   width: 100%;
  //   position: relative;
  //   display: grid;
  //   grid-template-columns: repeat(5, auto);
  //   margin: 20px 0;
  // }
</style>

