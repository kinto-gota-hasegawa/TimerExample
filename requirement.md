### Overview

UIは
- 今日の日付 (yyyy/MM/dd)
- 現在の時刻 (HH:mm:ss.SS)
- APIから取得した時刻まで残り何時間か (あと HH:mm:ss 有効)

を表示する。

### Details

- 今日の日付は1秒ごとに更新する？もし日付が切り替わった時に、更新されれば良い。
- 現在の時刻は1/100秒ごとに更新する。
- APIから取得した時刻は、1秒毎に更新する

### Technical Requirements
APIから取得は仮想的に、
"2025/10/30 23:00:56"
"あと %1$s 有効"
"HH:mm:ss"
というデータ届いた想定で考える。

またTimer処理はViewModelにおくと、画面が離れても、Timerが動き続けてしまうため、UI側に置く。

TimerProviderをComposeに直接DIできると良さそう。