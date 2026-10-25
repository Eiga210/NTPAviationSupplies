# NTPAviationSupplies（新北市空氣品質管理系統）

本專案為一套 Java Swing 桌面應用程式，用於管理「新北市空氣品質淨化區」資料，
提供資料的新增、查詢、更新、刪除（CRUD）以及多格式（CSV / JSON / XML）匯入匯出功能。
資料儲存於 SQL Server 資料庫，透過 JDBC 存取。

技術

| 項目 | 內容 |
| GUI | Java Swing（JFrame / JMenu / JOptionPane） |
| 資料庫 | SQL Server（透過 JDBC 連線） |
| 資料交換格式 | CSV、JSON（Jackson / Gson）、XML（DOM） |
| 開發環境 | Eclipse（.project / .classpath） |

系統架構

* *model* — @Air@：資料物件，對應資料表欄位（seqno, district, year, type, name, area_length, manager）
* *Dao* — @AirDao@：資料庫 CRUD 存取層；@WindowDemo@：主視窗 GUI 與操作邏輯；@DistrictSelection@ / @BaseTypeSelection@：下拉選單輸入元件；@DataViewer@：輸入驗證工具
* *Service* — @AirService@：將原始 CSV 字串轉換為 @Air@ 物件清單
* *utils* — @JDBCutil@：資料庫連線管理；@GetDataUtil@：CSV / JSON / XML 檔案讀取與解析
* *ExportData* — @DataExport@：將資料庫內容匯出為 CSV / JSON / XML
* *lab* — @DemoAir@：程式進入點（main），選擇匯入格式後啟動主視窗

主要功能

1. **選擇檔案格式匯入初始資料**
   - 支援 CSV、JSON、XML 三種檔案格式。

2. **新增空氣品質淨化區資料**
   - 新增一筆空氣品質淨化區資料，包含以下欄位：
     - 行政區
     - 年度
     - 類別
     - 名稱
     - 面積
     - 管理單位

3. **查詢資料**
   - 查詢全部資料。
   - 依 ID 查詢單筆資料。
   - 依行政區查詢單筆或多筆資料。
   - 依類別查詢單筆或多筆資料。

4. **更新既有資料**
   - 依 ID 找到指定資料，並更新相關欄位。

5. **刪除資料**
   - 依 ID 刪除指定的空氣品質淨化區資料。

## 資料庫連線設定

`src/jdbc.properties` 只保留連線字串（`url`），帳號密碼改由環境變數提供，執行前請先設定：

```sh
export DB_USERNAME=你的SQL Server帳號
export DB_PASSWORD=你的SQL Server密碼
```

或在 Eclipse/IntelliJ 的 Run Configuration 中加入這兩個環境變數。

連線目標為本機 SQL Server，資料庫名稱為 `JDBCwork`
（見 `src/jdbc.properties` 的 `url`）。
