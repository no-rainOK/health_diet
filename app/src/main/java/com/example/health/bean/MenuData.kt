package com.example.health.bean

import com.example.health.R
import java.util.Random


object MenuData {
    private val food = arrayOf(
        "茨菰蒜苗炒肉片", "酸菜鱼", "大白菜炖豆腐", "咖喱烤鸡", "蒜蓉粉丝蒸虾", "茄香鸡块",
        "苦瓜炒肉丝", "拔丝地瓜", "五花肉超油豆角", "猪肉韭菜包子", "咖喱牛肉饭", "萝卜烧鲳鱼",
        "腐竹香菇蒸排骨", "猪肉玉米饺子", "地三鲜", "芝士肉松", "阿胶固元膏", "红烧豆腐",
        "凉拌木耳竹笋", "小西红柿烧茄子", "酱香辣子鸡", "铜锣烧", "回民粉蒸肉", "和风咖喱鸡块饭",
        "花朵窝窝头", "豆腐水煮鱼", "美味鸡蛋酱", "清蒸大闸蟹", "酸奶蛋糕"
    )
    private val food1 = arrayOf(
        """
    步骤：
    1、准备好所有的食材。
    2、慈菇去皮切片。
    3、锅中水烧开放入茨菇焯烫一下。
    4、里脊肉切片加入糖，料酒，酱油淀粉腌拌均匀。
    5、炒锅倒油爆香葱姜，倒入肉片炒至变色盛出。
    6、然后倒入慈姑翻炒均匀。
    7、加入酱油。
    8、加入糖和盐翻炒均匀。
    9、在放入蒜苗翻炒。
    10、把炒过的肉片倒入翻炒。
    11、淋入水淀粉。
    12、再淋入香油翻炒均匀关火。
    """.trimIndent(),
        """
            步骤：
            1、青鱼一条，处理干净。
            2、酸菜，拆开，清洗后切段。
            3、切好蒜片、蒜叶碎、葱段。
            4、备好茴香、花椒、红椒。红椒没剪不会太辣。
            5、接下来处理青鱼。像图1那样把鱼平放，用刀从头部到鱼尾，在背上划下来。在鱼骨旁边划的，这样容易操作下一步。
            6、然后从鱼尾处向鱼头部平刀切入。以拉锯式向前。
            7、到鱼腮部切断。
            8、用同样方法片下另一半。
            9、然后 把骨头和头切开和酸菜放一起
            10、把鱼肚内的肚腩处的鱼刺片下来，切小块。
            11、然后，斜刀开始从鱼尾部片鱼片。也是以拉锯式往后片去。
            12、片好的鱼片，加入一个蛋清，一勺生粉，一勺油。
            13、加入适量胡椒粉、盐。
            14、然后，用手揉匀，按顺时方向，揉数分钟后静放待用
            15、锅里入油，炒香蒜片、葱、和花椒、干椒。
            16、加入酸菜和鱼骨翻炒。（此步可把泡椒一起入锅）
            17、加入开水或骨汤。烧开后，加入香醋、盐、生抽，炖约15分钟。
            18、然后把酸菜和鱼骨捞出
            19、然后把酸菜和鱼骨捞出
            20、鱼片煮好之后，用漏勺捞出来放在图18的盆中。
            21、然后把汤倒上。
            22、然后把汤倒上。
            23、净锅上把油烧热。（还可以加入一些红干椒）
            24、把热油淋在上面即可。
            """.trimIndent(),
        """
            步骤：
            1、主辅料：大白菜、豆腐。
            2、将白菜切条。
            3、洗净、沥干水分。
            4、起锅热油，爆香葱花。
            5、放入白菜，用中火煸炒至白菜塌秧。
            6、加入适量水。
            7、烧开后，大约炖制5分钟左右，至白菜约8分熟。
            8、放入豆腐，加盐调味。炖制3分钟左右。
            9、加味精提鲜，即可关火。
            10、出锅盛入碗中即可。
            """.trimIndent(),
        """
            步骤：
            1、将三黄鸡和葱姜蒜放入盆中， 加入适量的盐和生抽、白酒；
            2、再加入咖喱粉；
            3、拌均匀后用叉子在鸡表面上扎些小孔；
            4、再带上一次性手套帮助按摩均匀， 腌制三个小时以上；
            5、将姜葱放入电压力锅中垫底；
            6、再将腌制好的三黄鸡放在上面;
            7、按下压力锅上的无水焗的功能键；
            8、时间到， 这香喷喷的“咖喱鸡”就做好了。
            9、开吃了。
            """.trimIndent(),
        """
            步骤：
            1、准备材料。
            2、粉丝放锅中煮至变软捞出过凉备用。
            3、在煮粉丝的过程中，将虾头摘下。（头不要丢掉，可以炸虾油）
            4、将虾开背，一方面可以使虾更入味，另一方面可去虾线。
            5、将粉丝拌入少许的生抽，芝麻油。
            6、拌好的粉丝装入盘中。
            7、将处理好的虾放在粉丝上。
            8、蒜剁成细末。
            9、锅中放适量油烧热，倒入蒜末。
            10、将蒜末炒出香味，至变黄。
            11、将炒好的蒜末放在虾上。
            12、适量生抽、醋，加少许水，料酒调成碗汁。
            13、将调好的碗汁浇在虾上。
            14、锅中水烧热，放入虾，蒸至虾壳变红熟透。
            15、取几片葱叶用刀片压扁。
            16、用刀划成细丝。
            17、用凉水略泡至卷曲。
            18、将蒸好的的虾出锅，点缀葱丝上桌即可。
            """.trimIndent(),
        """
            步骤：
            1、鸡腿切小块。
            2、切好的鸡块放入碗中加葱姜、美极鲜和胡椒粉拌匀腌制20分钟。
            3、红椒去籽洗净切块。
            4、青椒洗净切小块。
            5、热锅下油，放入葱姜炝锅。
            6、加入腌制好的鸡块。
            7、翻炒至鸡块出油。
            8、加入白砂糖翻炒入味。
            9、再加入青、红椒翻炒至断生。
            10、加入亨氏番茄酱和高汤。
            11、旺火翻炒10秒钟关火成盘即可。
            """.trimIndent(),
        """
            步骤：
            1、猪肉切成丝，在加入料酒，生抽淀粉抓匀腌15分钟，然后上锅炒一下
            2、苦瓜清洗干净
            3、挖掉中间心
            4、切成小块放盐水里泡一下
            5、锅里加入油
            6、在加入苦瓜炒
            7、炒至变色
            8、在加入炒过的肉丝
            9、不停的翻炒
            10、加入盐炒匀
            11、炒至菜熟在加入鸡精
            12、炒好装盘
            13、看是不是美味
            """.trimIndent(),
        """
            步骤：
            1、制作拔丝的材料。
            2、地瓜洗净削皮备用。
            3、地瓜切成滚刀块。
            4、锅中加入适量的大豆油。
            5、油加热到5至6成热时候放入地瓜，调到中火。
            6、地瓜炸至外表变硬，颜色金黄捞出控油备用。
            7、把锅中的油倒出来，锅中放入适量的白糖。
            8、调到中小火，锅中的白糖局部开始融化。
            9、直到锅中的糖全部融化开，调到微火。
            10、继续熬制白糖，熬成像蜂蜜一样的浓汁。
            11、再稍微熬制一会儿，糖汁略微变红时即可，这时候的火候一定要小。
            12、倒入炸好的地瓜块快速翻拌均匀，即可出锅装盘。
            """.trimIndent(),
        """
            步骤：
            1、材料：油豆角、五花肉
            2、将油豆角摘洗干净、捞出。
            3、放在案板上切成丝，待用。
            4、将五花肉放在案板上切成丝，待用。
            5、随后，将切好的油豆角下入沸水中焯开，至断生，捞出。
            6、烧锅倒油烧热，下入切好的五花肉，加少许料酒
            7、翻炒翻炒。
            8、接着，合入焯好的油豆角翻炒一下。
            9、然后，加少许生抽。
            10、加少许盐。
            11、加少许鸡精。
            12、调味炒匀，即成。
            13、出锅，装盘。
            """.trimIndent(),
        """
            步骤：
            1、拿一个小碗装一点安琪酵母
            2、用温水将安琪酵母泡开
            3、倒入白面中
            4、再倒入温水用筷子搅拌一下
            5、待面都粘连一起用手揉成面团
            6、盖上湿的笼屉布，在盖锅盖，等待起发（等待起发的过程中，就可以准备其他的：剁肉馅儿、切韭菜、切葱花姜末等）
            7、韭菜包完放水里浸泡一会
            8、拿出来放笼屉上控水
            9、肉快自然解冻后
            10、解冻好洗干净
            11、切成小碎块儿
            12、再剁成肉馅
            13、葱姜切成末儿
            14、现在是大热天，有两三个小时面就发得高高的，笼屉布用凉水拍一下就可以很容易的揭开了
            15、面放在面板上揉到表面光滑不粘手
            16、在盖上盖子醒发一会
            17、韭菜切成大小均匀的小碎段儿
            18、肉馅里面放1个生鸡蛋、花生油、酱油、葱花姜末，朝一个方向搅拌均匀
            19、再将剁好的韭菜放入肉馅中（先不要放盐和味精搅拌，因为放了盐韭菜就会出水儿，会不太好包，什么时候面弄好了什么时候搅拌）
            20、取一小块面
            21、成N个季子
            22、擀季子，中间厚周边薄
            23、待包子皮擀好后，放盐和味精搅拌均匀（如果不确定咸淡，可以弄一点肉馅放在不粘锅上煎一下尝尝再进行调整）
            24、放一勺馅儿
            25、然后顺着边儿依次的捏掐折叠，最有捏紧
            26、都包好了，蒸锅开火把水烧热
            27、锅里水煮开后，放笼屉，笼屉布浸湿后铺在笼屉上，把包子摆在上边，一定留有缝隙
            28、这是第二层，中大火，蒸15分钟即可
            29、包子出锅，配上银耳大黄米粥最好不过了，吃韭菜多了咧心，小米和大黄米可以消除这种情况
            """.trimIndent(),
        """
            步骤：
            1、制作咖喱饭的材料。
            2、洋葱、土豆、胡萝卜都切成小丁备用。
            3、牛里脊也切成小丁状备用。
            4、冷锅凉油加热到合适温度后调到适宜温度稍停一会儿。
            5、放入牛肉翻炒一会儿。
            6、加入胡萝卜翻炒一会儿。
            7、加热洋葱丁炒出香味。
            8、最后加入土豆丁炒至断生。
            9、在锅内加入开水。
            10、大火烧开后，改小火煮一会，把蔬菜煮制半熟。
            11、先关火，然后把咖喱放入锅里直到溶化。
            12、再次打开火，继续煮至咖喱变得浓稠的时候即可。
            """.trimIndent(),
        """
            步骤：
            1、鲳鱼治净，两面打花刀，加入盐、料酒和姜片腌制片刻；
            2、白萝卜切薄片（尽量薄一点儿）；
            3、葱姜蒜切片，红辣椒和香葱切粒；
            4、鲳鱼均匀裹上适量干面粉，入锅前抖掉多余的面粉；
            5、用适量食用油将鲳鱼煎制两面金黄；
            6、另起炒锅，爆香葱姜蒜片；
            7、加入鲳鱼和萝卜片，同时加入酱油；
            8、加入料酒，晃动炒锅，让调味料均匀分布；
            9、加入白糖和鸡精，晃动均匀；
            10、加入开水，略没过食材，大火烧开后，小火炖至鱼熟；
            11、用适量水淀粉勾薄芡；
            12、撒上香葱粒和红椒粒，即可。
            """.trimIndent(),
        """
            步骤：
            1、新鲜排骨400克
            2、准备好调料，主要的调料有腐乳、生抽、沙茶酱，蚝油，还有料酒，淀粉
            3、将排骨用沸水焯去污水
            4、洗净沥干备用
            5、用一勺生抽，一勺沙茶酱，一勺花生油，一勺蚝油，3块腐乳，少许料酒，少许淀粉，少许盐调成汁
            6、将排骨用调好的汁液拌均匀，盖上保鲜膜入冰箱腌2小时
            7、新鲜香菇洗净切片
            8、垫于盘底
            9、把腌好的排骨码在香菇上面
            10、切少许姜丝、蒜末与少许辣椒末撒于排骨上面
            11、锅内水开后放进去，转中火蒸20分钟
            12、营养美味的一道菜就做成了
            """.trimIndent(),
        """
            步骤：
            1、做的过程分为两部分，内馅和饺子皮，当然，饺子可以买现成的，内馅也可以买现成。
            把馅料的材料准备好。
            2、玉米洗干净并沥干水分。
            3、葱洗干净，切成葱花备用。
            4、五花肉去皮剁成肉馅，加入所有调料，并搅拌上劲。
            5、加入玉米粒。
            6、拌匀后加入葱花。
            7、充分搅拌均匀。
            8、内馅做好了，开始做饺子皮。
            把饺子皮的材料准备好。
            9、把水慢慢加入面粉中，一边倒，一边用筷子轻轻搅拌，搅拌至片状。
            10、开始用手揉面，把面团揉到光滑，盖上保鲜膜，静止30分钟。
            11、取出，分割成适合自个案板的大小的份量，我分成三份。把面团分别擀成薄片。
            12、用分割模在擀好的面片上按一下。
            13、漂亮的饺子皮就出来了，往两面洒点干面粉，以防粘贴。
            14、之所以能分割出这么漂亮的小圆面片，全因为有它，个人觉得非常方便。
            15、开始包饺子了，准备好一碗清水，以防饺子皮不粘时可以点一下。
            16、取一片圆面片，放入内馅。
            17、将右端边角捏住，右手拇指向外轻推内侧皮，食指将外侧皮形成褶折，右手拇指奖褶折捏紧，慢慢地向前褶，并将两端封口处捏紧。
            18、包好的饺子。
            19、包了一大碟，虽然很努力了，但还是有个别饺子的皮给包破了。
            20、煮饺子，水开后下饺子，搅动热水，大火煮开后，再加点冷水，煮到饺子浮起来就可以了。
            21、挑一个卖相比较好的上上镜，呵呵。
            22、皮薄，馅厚，自己包的就是实在。
            23、后来，总结了一下，偶家的饺子之所以在包的时候就破皮，是因为往饺子皮沾水，偶抹的太多了，把皮都抹破了，看来革命尚未成功，还须继续努力呀。
            24、请随便吃。
            """.trimIndent(),
        """
            步骤：
            1、茄子1个。
            2、土豆一个。
            3、青椒2个。
            4、茄子切成滚刀块。
            5、切好的茄子。
            6、将切好茄子放一点盐腌制15--20分钟。（用盐腌过的茄子不吃油，就不需要放太多油来炸）
            7、将土豆也切成滚刀块。
            8、切好的土豆一定要用水冲洗干净，要不有淀粉的包裹无论如何下锅一定粘锅。
            9、青椒切成块。
            10、锅内放油，不需要放太多，比平时炒的菜多一点点。
            11、将油烧热，要烧到150度以上。（油一定要热，这样炸土豆就一定不会粘锅）。油热后放入土豆，放一点点油，盐别放太多哦，后面炒的时候还要放酱油的。先大火炸几分钟，感觉外皮都已经有一点焦脆，可以转中小火慢慢炸里面才容易熟。
            12、锅内炸土豆的时间，可以调调味汁。--生抽一勺，老抽半勺，3滴醋（不用放多，只是提味用），小半勺料酒，白糖一小茶勺，耗油少许，水适量。
            13、土豆炸好后捞出，将盐腌制的茄子用水清洗干净，挤干水气，放入热油中炸。（这个油是刚刚炸完土豆的油，不需要再加油了，但是也一定要热，热油加用盐腌制这个两个方法，炸茄子就不需要特别放太多的油，大家可以试试）
            14、将茄子捞出待用，这是炸土豆时放的油量，炸完土豆和茄子，锅内都还剩这些油呢。记住哦先用盐腌制，然后用热油炸，茄子一定不会吸油的。
            15、放入大蒜炒香再放入青椒，放一点点盐炒一炒，（别放多后面还放酱油和蚝油的）然后放入土豆和茄子一起煸炒。
            16、倒入调味汁，翻炒3分钟勾芡收汁就可以了。
            17、装盘
            18、很下饭的哦！
            19、喜欢吃肉的，还可以加点肉，但是一定要加五花肉。将五花肉切薄片，在土豆茄子炸好后，装盘待用时放入五花肉，先炒干水气，加一小半勺酱油和料酒将五花肉煸炒到微干，这样不油腻，再放入大蒜和后面的步骤。我这个没加肉，呵呵因为家里没肉了。
            """.trimIndent(),
        """
            步骤：
            1、将猪后腿肉洗净切大块
            2、煮锅放水烧热将切好的肉放入，水开后转小火慢煮
            3、肉煮熟后捞出控水
            4、将熟猪肉装入保鲜袋
            5、用擀面杖擀碎
            6、然后用手将相连的的肉撕开，挑去猪肉里的筋
            7、选择面包机的果酱功能，然后将肉碎倒入面包机桶。
            8、鲜味酱油3大勺放入
            9、将花生油2大勺放入
            10、粗砂糖2大勺放入
            11、起动面包机果酱程序，一个程序结束后（70分钟）
            12、加入20克卡夫芝士粉然后再次启动果酱程序继续烘烤。
            13、烘烤至自己喜欢的颜色和干湿度即可。我的二次果酱程序，烘烤了40分钟。
            14、将烘烤完毕的肉松自然冷却至室温，然后装罐密封保存。
            """.trimIndent(),
        """
            步骤：
            1、材料：阿胶125克，黑芝麻（炒熟）250克，核桃仁250克，红枣去核250克，桂圆125克，枸杞125克，冰糖125克(我只用75克)，黄酒500克[注：该方参考山东文艺出版社的“阿胶文化与应用”特别添加了桂圆和枸杞].。
            2、阿胶板砸成丁块放入特制研磨机研成粉（这一步是阿胶专卖店完成）。
            3、研制好的阿胶粉末有特殊的香味，也可以伴食热牛奶。
            4、用微薄碗具盛500毫升黄酒加入阿胶粉搅拌均匀放入微波炉加热1分钟，取出搅拌使阿胶完全溶化，倒入全自动豆浆机加入另外的400毫升黄酒。
            5、核桃用微波炉打熟。
            6、核桃稍微碎一下放入容器备用。
            7、枣倒入豆浆机中。
            8、桂圆肉倒入豆浆机中。
            9、熟芝麻倒入豆浆机中。
            10、枸杞加入豆浆机中。
            11、豆浆机插上电源选择五谷功能按启动键。
            12、十几分钟后关闭电源开盖后的阿胶膏。
            13、另外加上冰糖。
            14、重新插上电源选择米浆功能（也可选择五谷）按启动键。
            15、十几分钟做好的阿胶膏。
            16、做好的阿胶膏倒入盛放核桃的容器中。
            17、搅拌均匀，晾透加密封盖放入冰箱。
            18、每日取1-2匙，直接服用或用温开水冲服，补肾养血美容养颜。
            """.trimIndent(),
        """
            步骤：
            1、将豆腐清洗干净，切成小块，装入盘中待用。
            2、平底锅烧热，放油，将豆腐放入锅中煎，煎至两面呈金黄色时。
            3、盛出，装入盘中待用。
            4、青椒切成片，葱姜切成末装入盘中待用。
            5、热锅放油，爆香葱姜末，青椒片。
            6、加入清汤，精盐，五香粉。
            7、倒入炸好的豆腐，稍微炖一会儿。
            8、最后加入味精调味，水淀粉勾芡。
            9、炒拌均匀，出锅，装入盘中即可。
            """.trimIndent(),
        """
            步骤：
            1、木耳、黄瓜备用。
            2、黄瓜洗净，切丝。
            3、竹笋切片。
            4、焯水备用。
            5、木耳洗净，切成小块焯水备用。
            6、黄瓜丝摆盘。
            7、摆上黑木耳。
            8、三种原料处理好后，摆盘，倒上适量的盐，醋，辣椒油和香油即可。
            """.trimIndent(),
        """
            步骤：
            1、材料集合
            2、茄子清洗干净，去蒂，切成滚刀块
            3、放入盆子中，撒上盐，搅拌均匀，腌制20分钟（这里加了盐，之后炒制的时候就不用再加了）
            4、用手挤出茄子被盐杀出来的水分，备用
            5、蒜头切成蒜蓉，小西红柿对半切，备用
            6、坐锅热油（油要比平常炒菜多），倒入茄子
            7、翻炒至茄子断生，倒入小西红柿，翻炒均匀
            8、然后调入适量酱油，翻炒均匀
            9、倒入蒜蓉，翻炒均匀
            10、调入适量提前准备好的水淀粉
            11、翻炒均匀
            12、出锅装盘，开吃吧
            """.trimIndent(),
        """
            步骤：
            1、市场买来的三黄仔鸡500g，切块、洗净，沥干水分备用。
            2、调料照片，自制老酱用温水调开备用。
            3、配料照片，大蒜洗净备用。生姜洗净切片，青、红椒切成块状备用。
            4、配料照片，大蒜洗净备用。生姜洗净切片，青、红椒切成条状备用。
            5、青蒜洗净切段备用
            6、锅中放入适量菜油、依次放入配料：草果、八角、干辣椒、花椒、大蒜、姜片煸炒出香味。
            7、锅中放入适量菜油、依次放入配料：草果、八角、干辣椒、花椒、干辣椒面、大蒜、姜片，用小火煸炒出香味。
            8、配料炒出香味后，放入仔鸡，小火翻炒至金黄。
            9、放入红辣椒、青辣椒翻炒。
            10、倒入调好的酱水炒一会儿，接着放入红辣椒、青辣椒翻炒。
            11、同时加入青蒜翻炒。
            12、最后放入老抽，盐，味精调味后起锅
            13、装盘即成一道香辣美味的酱香辣子鸡。
            """.trimIndent(),
        """
            步骤：
            1、准备好原材料
            2、蛋清蛋黄分离，装蛋清的盆不要沾水沾油。
            3、鸡蛋中加入牛奶，搅拌均匀。
            4、面粉加一小勺泡打粉筛入蛋黄液里。
            5、与蛋黄液搅拌均匀至无颗粒，面粉不要太多，搅拌完之后用勺子舀起能匀速不费劲的流下就可以，当然也不要稀的跟面汤似的。
            6、搅拌蛋白，搅拌途中加入白糖，打至提起打蛋器时，尖端软软挂下来即可。
            7、1，将打好的蛋白加入蛋黄液。
            8、将打好的蛋白加入蛋黄液。
            9、2，上下搅拌，不要搅拌过度，让混合液舀起能均匀流下就可以了，然后放置10分钟稍微发一会。
            10、平底不粘锅，不放油加热，中火，舀一勺面糊铺在锅底，形状尽量圆一些。
            11、加热十几秒到三十秒左右，用木铲轻轻铲一下，看看底面凝固没，凝固了就用铲子小心翻面再加热10秒左右铲起出锅，重复这个步骤直至把所有面糊烙完。烙好的均匀铺在案板或者烤架上散热，不要堆在一起。
            12、刚做好的味道倍儿棒哦~单吃蛋糊皮味道也很棒的~~
            13、将豆沙馅均匀的铺在烙好的饼饼上。
            14、盖上另一张饼。
            15、嘿嘿 我没等全部做完的时候就忍不住啃了一个了~~~
            16、成品
            """.trimIndent(),
        """
            步骤：
            1、牛肉一定要选择牛肋条，肥一点的
            2、切成大块，麻将的一半大小即可
            3、花椒30g、白芷5g用小火炒香
            4、炒香
            5、拣出花椒里面的硬粒
            6、擀成花椒白芷面备用
            7、肉中加适量葱段和姜片，加入生抽两大勺，料酒50g，盐25g左右，鸡精一小勺。
            8、倒入炒好的花椒白芷粉和五香粉10g
            9、用手抓匀腌制一夜（24小时更好）
            10、第二天挑拣出葱姜
            11、拌入面粉
            12、直至每块肉上都均匀沾上面粉，并且盆中无多余面粉即可
            13、用龙布包上裹上面粉的肉，大火蒸30分钟后转小火再蒸制2小时
            14、蒸好的肉用筷子打散后放到自然凉（所以一般是第一天蒸好后第二天吃）
            15、二次蒸制以便于入味，吃的时候只需热热即可。上边可以热上小饼配着吃
            """.trimIndent(),
        """
            步骤：
            1、先将鸡胸肉解冻切成自己觉得适合的鸡丁，撒上适量食盐腌制半小时以上。
            2、将土豆和胡萝卜洗净切丁沥干水分备用。
            3、将锅烧热。
            4、倒入少量色拉油。
            5、将切好的土豆和胡萝卜倒入翻炒。
            6、炒至五六分钟后加入适量清水继续烧沸腾，将土豆和胡萝卜煮松软。
            7、加入事先准备好的咖喱酱。
            8、让咖喱酱和土豆胡萝卜充分融合在一起，煮至浓稠。
            9、在煮咖喱酱的同时，将腌制好的鸡丁沥掉水分后均匀裹上生粉。
            10、将鸡蛋液准备好。
            11、将裹好生粉的鸡丁倒入鸡蛋液中搅拌均匀。
            12、依次将鸡丁从鸡蛋液中捞出放进面包糠中裹上面包糠。
            13、将锅烧热倒入适量色拉油。
            14、待油烧至三成热时将鸡丁依次放入炸。
            15、待鸡丁炸至金黄色时即可捞出沥干油。
            16、将事先煮好的米饭盛入碗中用勺子按压紧实后倒入盘中。
            17、加入事先煮好的咖喱酱。
            18、加入炸好的鸡丁即可。
            """.trimIndent(),
        """
            步骤：
            1、准备所需食材
            2、将玉米粉、糖粉、糯米粉放在一起搅拌均匀，慢慢往里加水，揉成不粘手面团（不喜欢糯的可以少加糯米粉哈）
            3、放一边静置15分钟
            4、准备好模具
            5、用刷子在模具内刷上一层油（一定要刷哦，不然熟后不方便取出）
            6、取约25g左右面团揉圆，放于模具中间
            7、用大拇向周边轻轻推挤上去，成小碗状
            8、压好的样子
            9、用小刀把边上多余部分切掉
            10、做好后入蒸锅大火蒸上10分钟
            11、稍稍放凉些再轻轻将花朵从模具里取出
            12、将炒好的鸡丁用勺子舀于花朵碗内，装盘（炒鸡丁做法菜谱里有哈）
            """.trimIndent(),
        """
            步骤：
            1、净鱼肉一条；
            2、斜切大片；
            3、洗淘沥水；
            4、加入盐0.5茶匙、胡椒粉0.5茶匙、味精0.5茶匙、玉米淀粉1茶匙、料酒2茶匙；
            5、抓合均匀，净置10分钟；
            6、放水发红薯淀粉2茶匙，抓匀。
            7、干黄豆1汤匙，用清水沥洗约泡；
            8、沥干；
            9、冷锅冷油，小火下黄豆；
            10、待黄豆发出均匀叭叭声捞出。
            11、內脂豆腐1盒，切大块。
            12、菜油0.5汤匙，小火下泡姜颗20克、豆瓣酱4茶匙煎香；
            13、倒入清水，放胡椒粉1茶匙大火烧煮5分钟；
            14、放豆腐块；
            15、中火烧开后，小火煮5分钟捞入汤盆；
            16、鱼片分别放入；
            17、待汤汁微开，放鸡精1茶匙拌匀后倒入汤盆；
            18、洒糊辣块（干海椒10根切段、干花椒20颗在菜油中小火煎出糊辣味，冷却后用刀剁碎）、大蒜粒20克；
            19、菜油1.5汤匙，大火烧热，下用温水浸泡过的5根干海椒段；
            20、淋入鱼片上；
            21、洒油酥黄豆、2根小葱切成的颗即可。
            """.trimIndent(),
        """
            步骤：
            1、食材备用，鸡蛋打散。
            2、锅里放入花生油，放葱末爆香。
            3、倒入鸡蛋液。
            4、用铲子煸炒成形。
            5、把鸡蛋推到一边（也可以盛出来），倒入甜面酱。
            6、用铲子煸炒一会。
            7、然后把鸡蛋拌入。
            8、在煸炒一会充分炒匀。
            9、倒出密封保存，随吃随取。
            10、美味就这么简单。
            """.trimIndent(),
        """
            步骤：
            1、准备好的料.
            2、清洗干净，水里面加一点点盐养半小时，吐吐杂物。
            3、上笼大火蒸熟就可以。
            4、蒸熟的大闸蟹。
            5、生姜洗净去皮剁碎。
            6、加入适量的醋微波炉大火转30秒。
            7、蒸好的蟹沾上姜醋汁更加鲜美！
            """.trimIndent(),
        """
            步骤：
            1、酸奶150毫升。
            2、蛋黄4个加绵白糖搅拌均匀。
            3、放入酸奶和色拉油拌匀，把低筋粉筛入蛋黄糊中。
            4、上下翻拌均匀。
            5、蛋白中放入盐、砂糖快速搅打成中性发泡。
            6、将蛋白的三分之一倒入蛋黄糊中翻拌均匀。
            7、再倒入剩下的三分之二的蛋白中。
            8、上下翻版均匀。
            9、倒入八寸蛋糕模具中，震两下，将大气泡震出。活底模具包上锡纸放入加水的烤盘中。
            10、一起放入预热好的烤箱中。
            11、165C°烤8分钟，转150C°再烤50分钟即可。
            12、取出来倒扣钢架上，晾凉脱模。
            13、切开摆盘，即可享用。
            """.trimIndent()
    )
    private val resId = intArrayOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5,
        R.drawable.menu6,
        R.drawable.menu7,
        R.drawable.menu8,
        R.drawable.menu9,
        R.drawable.menu10,
        R.drawable.menu11,
        R.drawable.menu12,
        R.drawable.menu13,
        R.drawable.menu14,
        R.drawable.bamboo,
        R.drawable.menu16,
        R.drawable.menu17,
        R.drawable.menu18,
        R.drawable.menu19,
        R.drawable.menu20,
        R.drawable.menu21,
        R.drawable.menu22,
        R.drawable.menu23,
        R.drawable.menu24,
        R.drawable.menu25,
        R.drawable.menu26,
        R.drawable.menu27,
        R.drawable.menu28,
        R.drawable.menu29
    )
    private val foodjianjie = arrayOf(
        """
    主料：慈姑300克、里脊肉150克
    辅料：蒜苗50克、葱适量、盐适量、白糖3克、酱油20克、淀粉适量、香油适量、姜适量
    """.trimIndent(),
        """
            主料：青鱼800克、酸菜400克、蛋清1个
            辅料：小茴香适量、干椒适量、花椒适量、葱适量、姜适量、蒜头适量、蒜叶适量、盐适量、生抽适量、淀粉适量、食用油适量、芝麻适量
            """.trimIndent(),
        """
            主料：大白菜适量、豆腐适量
            辅料：葱花适量、食用油适量、盐适量、味精适量
            """.trimIndent(),
        """
            主料：三黄鸡1只
            辅料：姜适量、葱适量、大蒜适量、白酒2勺、咖喱粉1包、生抽3勺
            """.trimIndent(),
        """
            主料：海虾10只、粉丝适量
            辅料：葱适量、姜适量、大蒜适量、芝麻油适量、生抽适量、醋适量、盐适量、料酒适量
            """.trimIndent(),
        """
            主料：鸡腿400克、高汤50毫升
            辅料：青椒80克、红椒80克、葱15克、姜10克、胡椒粉2克、美极鲜15毫升、盐5克、白砂糖20克、番茄酱30克、花生油30毫升
            """.trimIndent(),
        """
            主料：地瓜1个
            辅料：白糖适量、大豆油适量
            """.trimIndent(),
        """
            主料：五花肉75克
            辅料：油豆角150克、料酒少许、生抽少许、盐少许、鸡精少许
            """.trimIndent(),
        """
            主料：猪肉半斤、韭菜1适量、生鸡蛋1个
            辅料：白面粉4碗、葱花适量、姜适量、酱油适量、盐适量、味精适量、香油适量、花生油适量
            """.trimIndent(),
        """
            主料：米饭适量、胡萝卜适量、牛里脊适量
            辅料：洋葱适量、咖喱适量、土豆适量、黑芝麻适量
            """.trimIndent(),
        """
            主料：鲳鱼1条
            辅料：大蒜适量、姜适量、白萝卜200克、葱适量、香葱粒适量、红辣椒粒适量、红烧酱油1大勺、料酒1大勺、白糖1小勺、鸡精适量、水淀粉适量、盐少许、食用油适量、面粉适量
            """.trimIndent(),
        """
            主料：排骨400克、鲜香菇200克
            辅料：盐适量、腐乳适量、生抽适量、蚝油适量、沙茶酱适量、花生油适量、姜丝适量、蒜末适量、红椒末适量
            """.trimIndent(),
        """
            主料：五花肉500克、玉米350克
            辅料：中筋面粉200克、葱4根、清水适量、芝麻油适量、酱油适量、鸡粉适量、砂糖适量、生粉适量
            """.trimIndent(),
        """
            主料：茄子一个、青椒2个、生抽1勺、醋3滴、蚝油适量
            辅料：盐适量、土豆一个、大蒜3瓣、老抽小半勺、白糖一茶勺、料酒半勺
            """.trimIndent(),
        """
            主料：熟猪后腿肉360克
            辅料：生抽酱油3大勺、粗砂糖2大勺、花生油2大勺、芝士粉20克
            """.trimIndent(),
        """
            主料：阿胶125克、核桃仁250克、大枣（去核）250克、桂圆125克、枸杞125克、冰糖125克、黄酒500克
            辅料：黑芝麻（炒熟）250克
            """.trimIndent(),
        """
            主料：豆腐300克
            辅料：无
            """.trimIndent(),
        """
            主料：木耳适量、竹笋适量
            辅料：黄瓜适量、盐适量、醋适量、香油适量、辣椒油适量
            """.trimIndent(),
        """
            主料：小西红柿适量、茄子适量
            辅料：蒜头适量、盐适量、酱油适量、水淀粉适量
            """.trimIndent(),
        """
            主料：三黄仔鸡500克
            辅料：大蒜适量、姜片适量、鲜红辣椒适量、鲜青辣椒适量、青蒜适量、红干辣椒10只、四川花椒适量、草果（拍散）2个、八角5个、干红辣椒面少许、自制老酱（昭通酱）50克、老抽少许、味精少许、盐适量
            """.trimIndent(),
        """
            主料：面粉适量、鸡蛋适量、牛奶适量
            辅料：白糖适量、泡打粉适量
            """.trimIndent(),
        """
            主料：牛肋条肉1000克、白芷5克
            辅料：姜适量、面粉适量、大蒜适量、葱适量、五香粉10克、花椒30克、生抽30克、料酒50克
            """.trimIndent(),
        """
            主料：鸡胸肉1块、胡萝卜1个、鸡蛋2个
            辅料：土豆1个、食盐适量、咖喱酱1盒、生粉适量、面包糠适量、色拉油适量
            """.trimIndent(),
        """
            主料：糯米粉50克、玉米粉100克、糖粉一勺
            辅料：小蛋挞模具10个
            """.trimIndent(),
        """
            主料：净鱼肉约400克、干黄豆1汤匙、內脂豆腐1盒
            辅料：盐0.5茶匙、味精0.5茶匙、料酒2茶匙、鸡精1茶匙、泡姜20克、干海椒15根、大蒜20克、胡椒粉1.5茶匙、玉米淀粉1茶匙、红薯淀粉2茶匙、菜油2汤匙、豆瓣酱4茶匙、干花椒20颗、小葱2根
            """.trimIndent(),
        """
            主料：鸡蛋3个
            辅料：甜面酱250克、葱末适量、花生油适量
            """.trimIndent(),
        """
            主料：大闸蟹3个
            辅料：甜面酱250克、葱末适量、花生油适量
            """.trimIndent(),
        """
            主料：黑鱼一条
            辅料：葱一段、盐两茶匙
            """.trimIndent(),
        """
            主料：鸡蛋4个
            辅料：酸奶150毫升、低筋粉75克、绵白糖30克、砂糖40克、盐2克、色拉油20毫升
            """.trimIndent(),
        """
            主料：鸡蛋4个
            辅料：酸奶150毫升、低筋粉75克、绵白糖30克、砂糖40克、盐2克、色拉油20毫升
            """.trimIndent()

    )
    val allFoodList: List<FoodBean>
        get() {
            val list: MutableList<FoodBean> = ArrayList()

            for (i in food.indices) {
                val bean = FoodBean(food[i], food1[i], foodjianjie[i], resId[i])
                list.add(bean)
            }
            randomList(list)
            return list
        }

    private fun randomList(list: MutableList<FoodBean>) {
        val random = Random()
        for (i in list.indices) {
            val randomPos = random.nextInt(list.size)
            val temp = list[i]
            list[i] = list[randomPos]
            list[randomPos] = temp
        }
    }
}
