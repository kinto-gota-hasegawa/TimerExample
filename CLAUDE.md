# Overview

Kotlin DateTimeライブラリを使ったサンプルプロジェクト


# Features

- kotlinx-datetimeを使った日付と時刻の操作
- タイムゾーンの管理
- テストできるようにする
- ViewModelに時間を管理できるinterfaceを追加
- テストではfakeの時間を使う
- Composeで

# UI

```kt
class MainActivity: ComponentActivity() {
    private val viewModel: TimerViewModel by viewModels {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            TimerScreen(uiState = uiState)
        }
    }
}
```
```kt
@Composable
fun TimerScreen(
    uiState: TimeUiState
) {
    // 現在時刻を1秒ごとに更新して表示する
}
```
)

# ViewModel

```kt
class TimerViewModel(
    private val timeProvider: TimeProvider
): ViewModel {
    
}
```

# TimeProvider Interface

```kt
interface TimeProvider {
    // UIから1秒ごとにデータを取得したい
    // 現在時刻を取得
    // カウントダウンタイマーを取得
}
```