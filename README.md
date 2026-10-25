# NTPAviationSupplies

## 資料庫連線設定

`src/jdbc.properties` 只保留連線字串（`url`），帳號密碼改由環境變數提供，執行前請先設定：

```sh
export DB_USERNAME=你的SQL Server帳號
export DB_PASSWORD=你的SQL Server密碼
```

或在 Eclipse/IntelliJ 的 Run Configuration 中加入這兩個環境變數。

連線目標為本機 SQL Server，資料庫名稱為 `JDBCwork`
（見 `src/jdbc.properties` 的 `url`）。
