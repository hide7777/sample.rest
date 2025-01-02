<link href=".\style.css" rel="stylesheet"></link>

# RESTサンプルアプリ

このRESTサンプルアプリは、「yunosuke-github/sample-api」をforkし、改修したアプリです。作者様に感謝します。<BR>
以下に利用方法などを説明します。<BR>

Cpoyright　2025/1/2 auctor(尾﨑英一)

## リポジトリの複製
利用するgitは、以下のtortoisegitをWindowsへインストールすると良いでしょう。<BR>
https://tortoisegit.org/<BR>

gitを使って、当リポジトリをローカルの環境へクローン（複製）してください。<BR>
https://github.com/hide7777/sample.rest.git<BR>


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


## docker
Windows環境にDOCKERをインストールしてください。<BR>
Docker Desktop for windowsは、以下からダウンロードできます。<BR>
ユーザ登録に際して、ユーザ登録が必要です。<BR>
Docker Personalは無料ですので、ユーザ登録をおすすめします。<BR>
https://www.docker.com/ja-jp/<BR>


## Dockerコンテナを利用したORACLE環境の構築
以下の手順でORACLEで開発環境を構築できます。<BR>
本番業務で使用しない、かつ学習・アプリ開発などで使用する場合はORACLEを無償で利用できます。<BR>
必ずDocker Desktopを起動した状態、かつ、コマンドプロンプトで「docker login」を実行した状態で<BR>
以下の構築コマンドとORACLEのHPを参考に、FREE版のORACLE環境を構築して下さい。<BR>
https://www.oracle.com/jp/database/free/get-started/<BR>

＜構築コマンド＞<BR>
・docker pull container-registry.oracle.com/database/free:latest<BR>
・docker run -d --name oracle -p 1521:1521 -e ORACLE_PWD=<管理者パスワード> container-registry.oracle.com/database/free:latest<BR>

<ORACLE接続確認><BR>
sqlDeveloperなどのDBMS用ツールで接続できます。<BR>
サーバ：localhostまたはPCのIPアドレス<BR>
ポート：1521<BR>
サービス名：FREEPDB1<BR>
接続ユーザ：sys<BR>
SYSのパスワード：docker作成時に指定した<管理者パスワード><BR>


## テーブルの作成
DDLフォルダーに「create.sql」があります。<BR>
これを実行して、ORACLEに、ORACLE接続用スキーマと表「USERS」を作成し下さい。<BR>

当サンプルでは、接続用のユーザとして、[test_u1]を作って動かしています。<BR>


## 設定ファイルの変更
以下3ファイルにORACLEの接続情報を記載していますので、自分の環境に併せて設定値を変更して下さい。<BR>
上記の手順でORACLE環境を構築した場合には、変更の必要はありません。<BR>
sample.rest\src\main\resources\application.yml<BR>
sample.rest\src\test\resources\application.yml<BR>
sample.rest\src\test\java\com\example\sampleapi\config\DatasourceConfig.java


## EclipseのJDK設定
Eclipseを立ち上げます。<BR>
任意のワークスペースを指定して起動します。<BR>
・「ウインドウ」→「設定」→「Java」→「インストール済みのJRE」にて、「openjdk-17」にチェック<BR>
・「適用して閉じる」を押下<BR>


## プロジェクトの配置とコンパイル
以下の手順コンパイルします<BR>
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

以上




