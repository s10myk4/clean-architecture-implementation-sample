# クリーンアーキテクチャのサンプル実装 (A sample implementation of Clean Architecture)
ユースケース層の実装にフォーカスしたサンプル実装です
(This is a sample with a focus on the implementation of the usecase layer.)


```
[正常系]

ユーザーは、自分の所有している戦士の中から1人を選んでタップする
システムは、戦士の詳細画面を表示する
ユーザーは、武器装備ボタンをタップする
システムは、武器一覧を取得し、武器装備画面に表示する
ユーザーは、戦士に装備したい武器を一覧から選択して、タップする
システムは、選択した武器を装備するのに必要なレベル以上であるかを確認する
システムは、選択した武器の属性が戦士の属性が同じであるかを確認する
システムは、選択した武器を装備した戦士を作成し、保存する
システムは、"武器を変更しました"というメッセージを画面に表示する


[異常系]
・戦士のレベルが選択した武器を装備するのに必要なレベルを未満である
-> システムは、"この武器を装備するための条件を満たしていません"というメッセージを武器装備画面に表示する

・戦士の属性と選択した武器の属性が異なる
-> システムは、"この武器を装備するための条件を満たしていません"というメッセージを武器装備画面に表示する

・指定した戦士が存在しない
-> システムは、"対象の戦士が存在しません"というメッセージを戦士一覧画面に表示する
```
