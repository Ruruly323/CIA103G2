CREATE DATABASE IF NOT EXISTS dobuy;
USE dobuy;

CREATE TABLE CounterInform (
    counterInformNo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,       -- 櫃位訊息編號，主鍵
    counterNo INT NOT NULL,                         -- 櫃位編號，外來鍵
    informMsg VARCHAR(500) NOT NULL,               -- 通知訊息
    informDate DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 通知時間
    informRead TINYINT DEFAULT 0                    -- 已讀未讀 (0: 未讀, 1: 已讀)
   
);

-- 插入 20 筆假資料
INSERT INTO CounterInform (counterInformNo, counterNo, informMsg, informDate, informRead) 
VALUES 
(1, 1, '商品已上架', '2024-01-01 08:00:00', 0),
(2, 1, '新的促銷活動開始', '2024-01-02 09:00:00', 0),
(3, 2, '庫存不足，請補貨', '2024-01-03 10:00:00', 0),
(4, 2, '櫃位檢查通過', '2024-01-04 11:00:00', 1),
(5, 3, '本週特價商品', '2024-01-05 12:00:00', 0),
(6, 3, '請注意上架時間', '2024-01-06 13:00:00', 0),
(7, 4, '新商品已進貨', '2024-01-07 14:00:00', 1),
(8, 4, '促銷活動結束提醒', '2024-01-08 15:00:00', 0),
(9, 5, '顧客滿意度調查', '2024-01-09 16:00:00', 0),
(10, 5, '本月銷售報告', '2024-01-10 17:00:00', 1),
(11, 6, '櫃位維護通知', '2024-01-11 18:00:00', 0),
(12, 6, '即將到期的促銷活動', '2024-01-12 19:00:00', 1),
(13, 7, '商品回饋活動', '2024-01-13 20:00:00', 0),
(14, 7, '新年快樂的祝福', '2024-01-14 21:00:00', 0),
(15, 1, '本週營業時間變更', '2024-01-15 22:00:00', 1),
(16, 2, '顧客回饋及評價', '2024-01-16 23:00:00', 0),
(17, 3, '即將到貨商品', '2024-01-17 00:00:00', 0),
(18, 4, '促銷結束通知', '2024-01-18 01:00:00', 1),
(19, 5, '庫存檢查通知', '2024-01-19 02:00:00', 0),
(20, 6, '櫃位改裝計畫', '2024-01-20 03:00:00', 0);
