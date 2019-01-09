# A sample implementation of Clean Architecture
  
This is a sample with a focus on the implementation of the usecase layer.

#### Sample functional requirement(scenario) 
- The user can change the weapon equipped to the warrior.

#### Usecase description
```
[Normal case]
The user taps one person out of warriors owned by the user.
The system displays the detailed screen of the warrior.
The user taps the weapon equipped button.
The system acquires the weapon list and displays it on the weapon equipment screen.
The user selects a weapon that he wants to equip warrior from the list and taps.
The system checks whether the selected weapon is at or above the level required for equipping it.
The system checks whether the attribute of the selected weapon is the same as the attribute of the warrior.
The system creates and stores warriors equipped with selected weapons.
The system displays a message "weapon changed" on the screen.

Abnormal Case]
・　If the level of the warrior is less than the level required to equip the selected weapon
-> The system displays a message "We do not meet the conditions for equipping this weapon" on the weapon equipment screen.

・ When the attribute of the warrior and the attribute of the selected weapon are different
-> The system displays a message "We do not meet the conditions for equipping this weapon" on the weapon equipment screen.

・　If the designated warrior does not exist
-> The system displays a message "target warrior does not exist" on the warrior list screen.


in japanese
[正常系]
ユーザーは、自分の所有している戦士の中から1人を選んでタップする
システムは、戦士の詳細画面を表示する
ユーザーは、武器装備ボタンをタップする
システムは、武器一覧を取得し、武器装備画面に表示する
ユーザーは、戦士に装備したい武器を一覧から選択して、タップする
システムは、選択された武器が装備するために必要なレベル以上であるかを確認する
システムは、選択した武器の属性が戦士の属性が同じであるかを確認する
システムは、選択した武器を装備した戦士を作成し、保存する
システムは、"武器を変更しました"というメッセージを画面に表示する


[異常系]
・戦士のレベルが選択した武器を装備するのに必要なレベルを未満である場合
-> システムは、"この武器を装備するための条件を満たしていません"というメッセージを武器装備画面に表示する

・戦士の属性と選択した武器の属性が異なる場合
-> システムは、"この武器を装備するための条件を満たしていません"というメッセージを武器装備画面に表示する

・指定した戦士が存在しない場合
-> システムは、"対象の戦士が存在しません"というメッセージを戦士一覧画面に表示する
```
