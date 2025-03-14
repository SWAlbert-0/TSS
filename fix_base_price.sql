-- 更新目的地表中的基础票价
UPDATE destinations SET basePrice = 50.00 WHERE code = '2U0'; -- 总站
UPDATE destinations SET basePrice = 25.00 WHERE code = 'A01'; -- 北站
UPDATE destinations SET basePrice = 25.00 WHERE code = 'B02'; -- 南站
UPDATE destinations SET basePrice = 20.00 WHERE code = 'C03'; -- 东站
UPDATE destinations SET basePrice = 20.00 WHERE code = 'D04'; -- 西站
UPDATE destinations SET basePrice = 60.00 WHERE code = 'E05'; -- 机场
UPDATE destinations SET basePrice = 30.00 WHERE code = 'F06'; -- 大学城
UPDATE destinations SET basePrice = 35.00 WHERE code = 'G07'; -- 科技园
UPDATE destinations SET basePrice = 40.00 WHERE code = 'H08'; -- 商业区
UPDATE destinations SET basePrice = 30.00 WHERE code = 'I09'; -- 体育中心

-- 如果以上更新没有生效（可能是因为没有对应的记录），则插入新数据
INSERT INTO destinations (code, name, basePrice, description)
VALUES 
('2U0', '总站', 50.00, '中央车站总站'),
('A01', '北站', 25.00, '北部地区车站'),
('B02', '南站', 25.00, '南部地区车站'),
('C03', '东站', 20.00, '东部地区车站'),
('D04', '西站', 20.00, '西部地区车站'),
('E05', '机场', 60.00, '国际机场车站'),
('F06', '大学城', 30.00, '大学城区域车站'),
('G07', '科技园', 35.00, '科技园区车站'),
('H08', '商业区', 40.00, '中央商业区车站'),
('I09', '体育中心', 30.00, '体育中心区域车站')
ON DUPLICATE KEY UPDATE 
    name = VALUES(name),
    basePrice = VALUES(basePrice),
    description = VALUES(description); 