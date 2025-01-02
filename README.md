<link href="style.css" rel="stylesheet"></link>

# REST　アプリ　サンプル

（注）このサンプルアプリは、「yunosuke-github/sample-api」をforkした上で、改装しています。

---
## JDK
以下からOpenJDK 17(openjdk-17.0.0.1+2_windows-x64_bin.zip)をダウンロードし、ZIP展開後、任意の場所に配置して下さい。<BR>
https://jdk.java.net/java-se-ri/17-MR1

ここでは「C:\jdk\openjdk-17」に配置したとします。

次に、以下の手順でWindowsの環境変数JAVA_HOMEとPATHに登録します。<BR>
・Windowsの検索から、環境変数またはenvと入力して「環境変数を編集」をクリック<BR>
・ユーザの環境変数にJAVA_HOMEを追加し、値は「C:\jdk\openjdk-17」を設定<BR>
・システム環境変数のPATHの一番上に「C:\jdk\openjdk-17」を設定<BR>
・コマンドプロンプトを開いて、「java -version」を実行し、Versionが17である事を確認して下さい。
>java -version<BR>
>openjdk version "17.0.0.1" 2024-07-02<BR>
>OpenJDK Runtime Environment (build 17.0.0.1+2-3)<BR>
>OpenJDK 64-Bit Server VM (build 17.0.0.1+2-3, mixed mode, sharing)<BR>

## Eclipse 
日本語が使えるEclipseをダウンロードして下さい。<BR>
以下の「Pleiades All in One」のEclipseは、日本語化されているのでおすすめです。<BR>
https://willbrains.jp/<BR>
ダウンロードしたら、Cドライブの階層の浅い場所に配置して下さい。<BR>

ここでは「C:\Pleiades」に配置したとします。<BR>

なお、Pleiadesに付属しているJDK17はmaven実行が出来ない不具合があります。<BR>
その回避策としてJDK17を別途ダウンロードして設定済みです。<BR>
よって、Pleiadesの場合には、付属するJDK17（C:\Pleiades\日付フォルダ\java\17）を削除してください。<BR>

## ORACLE



## 設定ファイルの変更

## EclipseのJDK設定
Eclipseを立ち上げます。<BR>
任意のワークスペースを指定して起動します。<BR>
・「ウインドウ」→「設定」→「Java」→「インストール済みのJRE」にて、「openjdk-17」にチェック<BR>
・「適用して閉じる」を押下<BR>

## プロジェクトの配置とコンパイル
以下の手順コンパイルします<BR>
・gitを使って、以下のリポジトリをローカルの環境へクローン（複製）してください。<BR>
　https://github.com/hide7777/sample.rest.git<BR>
・Eclipseにて「ファイル」→「インポート」にて、インポートが画面を開く<BR>
・「Maven」→「既存Mavenプロジェクト」を選択<BR>
・ルートディレクトリに、当プロジェクトを指定し、「完了」を押下する。<BR>
・プロジェクトを右クリック→実行→Maven Clean→Maven Installを実行します。<BR>

## 実行
SampleApiApplicationにmainメソッドがあります。<BR>
このファイル上で右クリック→「実行」→「Javaアプリケーション」でアプリが立ち上がります。<BR>

## REST　API を呼び出す。
Eclipseで実行中にしておけば、<BR>
Postmanなどの、REST APIを呼び出せるツール使って、以下のAPIの動作確認が出来ます。<BR>




