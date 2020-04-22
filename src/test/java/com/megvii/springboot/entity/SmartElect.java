//package com.megvii.springboot.entity;
//
//import com.alibaba.fastjson.JSON;
//import com.leniao.leniaorestful.common.nbiot.BytesHexStrTranslate;
//import com.leniao.leniaorestful.common.nbiot.StringUtil;
//import com.leniao.leniaorestful.common.nbiot.SystemConst;
//import com.leniao.leniaorestful.common.pojo.BaseData;
//import com.leniao.leniaorestful.common.utils.CacheRealValue;
//import com.leniao.leniaorestful.pojo.business.angular.WebResult;
//import com.leniao.leniaorestful.pojo.business.push.ErrInfo;
//import com.leniao.leniaorestful.pojo.business.push.RedisErrInfoList;
//import com.leniao.leniaorestful.pojo.leniao_webapientity.Web_WisdomElectricity;
//import com.leniao.leniaorestful.pojo.leniao_webapientity.Web_staticType;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @program: leniaorestful
// * @description: 智慧用电
// * @author:
// * @create: 2019-05-20 11:40
// **/
//public class SmartElect extends BaseProtocol {
//
//        private final String _habase_desc = "L1|T1|T2|T3|T4|Ia|Ib|Ic|Va|Vb|Vc|QA|QB|QC|PA|PB|PC|DA|DB|DC|FA|FB|FC|A1|A2|A3|BV|BI|CSQ|VAB|VBC|VCA|IAB|IBC|ICA|MXP".toUpperCase();
//
//        public SmartElect() {
//            this._device_TypeId = 143;
//        }
//
//        @Override
//        boolean Analysis(BaseData baseData) {
//            try {
//                if (baseData != null) {
//                    String UDPData = baseData.getUdpData();
//                    int start = 0;
//                    String head = UDPData.substring(start, start+4);
////                String temp = UDPData.substring(4).replaceAll("FF00","FF");
//                    String temp = UDPData.substring(4);
//                    start += 4;
//                    String packetLength = UDPData.substring(start,start+2);
//                    start = packetLength.equalsIgnoreCase("FF") ? start + 4 : start + 2;
//                    String packetType = UDPData.substring(start, start + 2);
//                    start = packetType.equalsIgnoreCase("FF") ? start + 4 : start + 2;
//                    String deciceSign = UDPData.substring(start,start + 4); // 0x0023
//                    start += 4;
//                    StringBuffer sb = new StringBuffer();
//                    String s ;
//                    for (int i = 0; i < 12; i++) {
//                        s = UDPData.substring(start,start+2);
//                        sb.append(s);
//                        if (s.equalsIgnoreCase("FF")) {
//                            start += 4;
//                        } else {
//                            start += 2;
//                        }
//                    }
//
//                    // 统一回复设备
////                    if ("40".equalsIgnoreCase(packetType) == false) {
////                        response(baseData.getDeviceIdPk(), baseData.getDevsignature(), "A2");
////                    }
//
////                String hostNumber = UDPData.substring(8,32);
//                    String hostNumber = sb.toString();
//                    String data = UDPData.substring(start,UDPData.length()-2);
//                    baseData.setUdpPacketType(packetType); // 暂时使用该参数
//
//                    if ("22".equalsIgnoreCase(packetType)){
//                        // 读取参数回复22；解析数据并保存
//                        deviceParamsAnalysis(data,baseData);
//                    } else if ("24".equalsIgnoreCase(packetType)) {
//                        // 设备上传实时值24；解析数据，保存并推送
//                        uploadData(data,baseData);
//                    } else if ("40".equalsIgnoreCase(packetType)) {
//
//                        // 流程化处理，设备控制结果返回
//                        processizeCommand(baseData.getDeviceId(), "", 0);
//                        // 设置参数成功回复40；发送读取参数命令，获取最新参数
//                        requestNewestData(data, baseData); // xxxxxxxxxxxxxxx
//                    } else if ("27".equalsIgnoreCase(packetType)) {
//                        // IMEI号和IMSI号数据协议处理   智慧用电和灭弧
//                        IMEIAndIMSIAnalysis(data,baseData);
//                    } else if ("01".equalsIgnoreCase(packetType) || "02".equalsIgnoreCase(packetType)
//                            || "03".equalsIgnoreCase(packetType) || "04".equalsIgnoreCase(packetType)) {
//                        // 流程化处理，设备控制结果返回
//                        processizeCommand(baseData.getDeviceId(), "", 0);
//                        CacheRealValue.map.put(baseData.getDevsignature()+String.format("%02x", (0x80 + Integer.parseInt(packetType,16))),"true|" + System.currentTimeMillis());
//                    }
//                    else {
//                        return false;
//                    }
//
//                    return true;
//                }
//
//            } catch (Exception e) {
//                this.logger.error(e.getMessage(), e);
//            }
//            return false;
//        }
//
//        /**
//         * 发送读取参数命令
//         *
//         * @param baseData
//         * @return
//         */
//        private boolean requestNewestData(String data, BaseData baseData) {
//            if (null == baseData){
//                return  false;
//            }
//            try {
//                if (data != null && data.length() > 0) {
//                    // 解析参数
//                    boolean f = deviceParamsAnalysis(data,baseData);
//                    if (f) {
//                        logger.info("智慧用电" + baseData.getDevsignature() +"参数设置成功，数据解析完成");
//                    } else {
//                        logger.info("智慧用电" + baseData.getDevsignature() +"参数设置成功，数据解析失败");
//                    }
//                } else {
//                    CacheRealValue.map.put(baseData.getDevsignature()+String.format("%02x", (0x80 + Integer.parseInt(baseData.getUdpPacketType(),16))),
//                            "true|" + System.currentTimeMillis());
//                }
//
//                return true;
//
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                return false;
//            }
//        }
//
//        /**
//         * 上传实时数据
//         * @param data
//         * @param baseData
//         * @return
//         */
//        private boolean uploadData(String data, BaseData baseData) {
//            if (null == data){
//                return false;
//            }
//            try {
//
//                int[] intData = BytesHexStrTranslate.doubleHexStrToIntArray(data);
//
//                String xh = Integer.toHexString(intData[0]); // 型号标志位
//
//                // 转成2进制数
//                String warnStatus = Integer.toBinaryString(intData[1]);
//                // 左补0
//                warnStatus = StringUtil.PadLeft(warnStatus,16);
//
//                int[] status = new int[16];
//                for (int i = 0; i < 16; i++) {
//                    status[i] = Integer.parseInt(warnStatus.substring(i,i+1));
//                }
//
//                int OT_A = status[15]; // A相电流过载
//                int OT_B = status[14]; // B相电流过载
//                int OT_C = status[13]; // C相电流过载
//
//                int Va_I = status[12]; // A相电压中断 interrupt
//                int Vb_I = status[11]; // B相电压中断 interrupt
//                int Vc_I = status[10]; // C相电压中断 interrupt
//
//                int Va_dp = status[9]; // A相电压缺相 default phase
//                int Vb_dp = status[8]; // B相电压缺相 default phase
//                int Vc_dp = status[7]; // C相电压缺相 default phase
//
//                int LVa = status[6]; // A相电压低压
//                int LVb = status[5]; // B相电压低压
//                int LVc = status[4]; // C相电压低压
//
//                int HVa = status[3]; // A相电压高压
//                int HVb = status[2]; // B相电压高压
//                int HVc = status[1]; // C相电压高压
//
//                // 转成2进制数
//                String warnStatus2 = Integer.toBinaryString(intData[2]);
//                // 左补0
//                warnStatus2 = StringUtil.PadLeft(warnStatus2,16);
//
//                int[] status2 = new int[16];
//                for (int i = 0; i < 16; i++) {
//                    status2[i] = Integer.parseInt(warnStatus2.substring(i,i+1));
//                }
//
//                int L = status2[15]; //漏电报警
//
//                int T1 = status2[14]; //A相温度报警
//                int T2 = status2[13]; //B相温度报警
//                int T3 = status2[12]; //C相温度报警
//                int Tn = status2[11]; //N相温度报警
//
//                int FAULT_L = status2[10]; //漏电故障
//
//                int FAULT_T1 = status2[9]; //A相温度故障
//                int FAULT_T2 = status2[8]; //B相温度故障
//                int FAULT_T3 = status2[7]; //C相温度故障
//                int FAULT_Tn = status2[6]; //N相温度故障
//
//                int warnValue__L = intData[3]; // 报警漏电值
//
//                int warnValue__T1 = intData[4]; // 报警温度1值
//                int warnValue__T2 = intData[5]; // 报警温度2值
//                int warnValue__T3 = intData[6]; // 报警温度3值
//                int warnValue__Tn = intData[7]; // 报警温度4值
//
//                double warnValue_Ia = intData[8]/10d; // A相报警电流值
//                double warnValue_Ib = intData[9]/10d; // B相报警电流值
//                double warnValue_Ic = intData[10]/10d; // C相报警电流值
//
//                double warnValue__Va = intData[11]/10d; // A相报警电压值
//                double warnValue__Vb = intData[12]/10d; // B相报警电压值
//                double warnValue__Vc = intData[13]/10d; // C相报警电压值
//
//                int value_L = intData[14]; // 实时漏电值
//
//                int value_T1 = intData[15]; // 实时温度1值
//                int value_T2 = intData[16]; // 实时温度2值
//                int value_T3 = intData[17]; // 实时温度3值
//                int value_Tn = intData[18]; // 实时温度3值
//
//                double value_Ia = intData[19]/10d; // A相实时电流值 电流与电压1个小数点
//                double value_Ib = intData[20]/10d; // B相实时电流值 电流与电压1个小数点
//                double value_Ic = intData[21]/10d; // C相实时电流值 电流与电压1个小数点
//
//                double value__Va = intData[22]/10d; // A相实时电压值 电流与电压1个小数点
//                double value__Vb = intData[23]/10d; // B相实时电压值 电流与电压1个小数点
//                double value__Vc = intData[24]/10d; // C相实时电压值 电流与电压1个小数点
//
//                double Qa = ((intData[26] << 16) | intData[25])/10d; // A相电量 四个字节一个小数点，单位KWH
//                double Qb = ((intData[28] << 16) | intData[27])/10d; // B相电量 四个字节一个小数点，单位KWH
//                double Qc = ((intData[30] << 16) | intData[29])/10d; // C相电量 四个字节一个小数点，单位KWH
//
//                double apA = ((intData[32] << 16) | intData[31])/10d; // A相视载功率：四个字节一个小数点，单位W
//                double apB = ((intData[34] << 16) | intData[33])/10d; // B相视载功率：四个字节一个小数点，单位W
//                double apC = ((intData[36] << 16) | intData[35])/10d; // C相视载功率：四个字节一个小数点，单位W
//
//                double pfA = intData[37]/1000d; // A相功率因数：两个字节三个小数点
//                double pfB = intData[38]/1000d; // B相功率因数：两个字节三个小数点
//                double pfC = intData[39]/1000d; // C相功率因数：两个字节三个小数点
//
//                double Fa = intData[40]; // A相频率：两个字节无小数点
//                double Fb = intData[41]; // B相频率：两个字节无小数点
//                double Fc = intData[42]; // C相频率：两个字节无小数点
//
//                // 转成2进制数
//                String warnStatus3 = Integer.toBinaryString(intData[43]);
//                // 左补0
//                warnStatus3 = StringUtil.PadLeft(warnStatus3,16);
//
//                int[] status3 = new int[16];
//                for (int i = 0; i < 16; i++) {
//                    status3[i] = Integer.parseInt(warnStatus3.substring(i,i+1));
//                }
//
//                int eleA_mi = status3[15]; // A相电流互感器穿心方向 mutual inductor
//                int eleB_mi = status3[14]; // B相电流互感器穿心方向 mutual inductor
//                int eleC_mi = status3[13]; // C相电流互感器穿心方向 mutual inductor
//
//                int FAULT_arcA = status3[12]; // A相故障电弧
//                int FAULT_arcB = status3[11]; // B相故障电弧
//                int FAULT_arcC = status3[10]; // C相故障电弧
//
//                int vb = status3[9]; // 3相电压平衡度
//                int cb = status3[8]; // 3相电流平衡度
//
//                int MXP = status3[7]; // 大功率负载
//
//                int SO_Va = status3[6]; // A相电压短路 short out
//                int SO_Vb = status3[5]; // B相电压短路 short out
//                int SO_Vc = status3[4]; // C相电压短路 short out
//
//                int warn_arcA = intData[44]; //A相故障电弧量报警值  两个字节无小数点
//                int warn_arcB = intData[45]; //B相故障电弧量报警值  两个字节无小数点
//                int warn_arcC = intData[46]; //C相故障电弧量报警值  两个字节无小数点
//
//                int value_arcA = intData[47]; //A相故障电弧量实时值  两个字节无小数点
//                int value_arcB = intData[48]; //B相故障电弧量实时值  两个字节无小数点
//                int value_arcC = intData[49]; //C相故障电弧量实时值  两个字节无小数点
//
//                double warn_vb = intData[50]/10d; //3相电压平衡度报警值  两个字节一个小数点
//                double value_vb = intData[51]/10d; //3相电压平衡度实时值  两个字节一个小数点
//
//                double warn_cb = intData[52]/10d; //3相电流平衡度报警值  两个字节一个小数点
//                double value_cb = intData[53]/10d; //3相电流平衡度实时值  两个字节一个小数点
//
//                int CSQ = intData[54]; // 信号强度值
//                if (CSQ == 99) { // 99为信息极弱
//                    CSQ = 1;
//                }
//
//                double Vab = intData[55]/10d; // Va电压相位角   两个字节一个小数点
//                double Vbc = intData[56]/10d; // Vb电压相位角   两个字节一个小数点
//                double Vca = intData[57]/10d; // Vc电压相位角   两个字节一个小数点
//
//                double Iab = intData[58]/10d; // Ia电流相位角   两个字节一个小数点
//                double Ibc = intData[59]/10d; // Ib电流相位角   两个字节一个小数点
//                double Ica = intData[60]/10d; // Ic电流相位角   两个字节一个小数点
//
//                double warn_MXP = ((intData[62] << 16) | intData[61])/10d; //大功率负载报警值 四个字节一个小数点，单位W
//
//                //*************************解析结束*********************************
//
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String systemTime = baseData.getEventTime();// 获取系统时间
//                //更新在线时间
//                UpdateDeviceOnline(baseData);
//
//                //获取隐患故障集合
//                WebResult result = nosqlServcie.hget(baseData.getDeviceId(), SystemConst.KeyErrInfos);
//                if (result.getBackCode() == 0) {
//                    return false;
//                }
//                String strerrInfos = (String) result.getData();
//                RedisErrInfoList errinfos = JSON.parseObject(strerrInfos, RedisErrInfoList.class);
//                //异常集合
//                List<ErrInfo> list = new ArrayList<ErrInfo>();
//
//                //电流过载
//                ErrInfo errInfo0 = new ErrInfo();
//                errInfo0.setErrType(163);//过载
//                errInfo0.setNode("Ia");
//                errInfo0.setErrVal(warnValue_Ia);
//                errInfo0.setValUin("A");
//                errInfo0.setErrIsFg(OT_A);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo0.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo0.setIsSave(0);//是否需要保存
//                errInfo0.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo0.setXh(0);//序号
//                if (ConsumeErrInfoInRedis(errInfo0, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo0, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo0);
//                    }
//                }
//
//                //电流过载
//                ErrInfo errInfo1 = new ErrInfo();
//                errInfo1.setErrType(163);//过载
//                errInfo1.setNode("Ib");
//                errInfo1.setErrVal(warnValue_Ib);
//                errInfo1.setValUin("A");
//                errInfo1.setErrIsFg(OT_B);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo1.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo1.setIsSave(0);//是否需要保存
//                errInfo1.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo1.setXh(1);//序号
//                if (ConsumeErrInfoInRedis(errInfo1, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo1, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo1);
//                    }
//                }
//
//                //电流过载
//                ErrInfo errInfo2 = new ErrInfo();
//                errInfo2.setErrType(163);//过载
//                errInfo2.setNode("Ic");
//                errInfo2.setErrVal(warnValue_Ic);
//                errInfo2.setValUin("A");
//                errInfo2.setErrIsFg(OT_C);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo2.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo2.setIsSave(0);//是否需要保存
//                errInfo2.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo2.setXh(2);//序号
//                if (ConsumeErrInfoInRedis(errInfo2, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo2, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo2);
//                    }
//                }
//
//                //电压中断
//                ErrInfo errInfo4 = new ErrInfo();
//                errInfo4.setErrType(162);//电源中断
//                errInfo4.setNode("Va");
//                errInfo4.setErrVal(0);
//                errInfo4.setValUin("--");
//                errInfo4.setErrIsFg(Va_I);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo4.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo4.setIsSave(0);//是否需要保存
//                errInfo4.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo4.setXh(4);//序号
//                if (ConsumeErrInfoInRedis(errInfo4, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo4, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo4);
//                    }
//                }
//
//                //电压中断
//                ErrInfo errInfo5 = new ErrInfo();
//                errInfo5.setErrType(162);//电源中断
//                errInfo5.setNode("Vb");
//                errInfo5.setErrVal(0);
//                errInfo5.setValUin("--");
//                errInfo5.setErrIsFg(Vb_I);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo5.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo5.setIsSave(0);//是否需要保存
//                errInfo5.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo5.setXh(5);//序号
//                if (ConsumeErrInfoInRedis(errInfo5, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo5, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo5);
//                    }
//                }
//
//                //电压中断
//                ErrInfo errInfo6 = new ErrInfo();
//                errInfo6.setErrType(162);//电源中断
//                errInfo6.setNode("Vc");
//                errInfo6.setErrVal(0);
//                errInfo6.setValUin("--");
//                errInfo6.setErrIsFg(Vc_I);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo6.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo6.setIsSave(0);//是否需要保存
//                errInfo6.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo6.setXh(6);//序号
//                if (ConsumeErrInfoInRedis(errInfo6, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo6, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo6);
//                    }
//                }
//
//                //电压缺相
//                ErrInfo errInfo7 = new ErrInfo();
//                errInfo7.setErrType(161);//电源中断
//                errInfo7.setNode("Va");
//                errInfo7.setErrVal(0);
//                errInfo7.setValUin("--");
//                errInfo7.setErrIsFg(Va_dp);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo7.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo7.setIsSave(0);//是否需要保存
//                errInfo7.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo7.setXh(7);//序号
//                if (ConsumeErrInfoInRedis(errInfo7, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo7, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo7);
//                    }
//                }
//
//                //电压缺相
//                ErrInfo errInfo8 = new ErrInfo();
//                errInfo8.setErrType(161);//电源中断
//                errInfo8.setNode("Vb");
//                errInfo8.setErrVal(0);
//                errInfo8.setValUin("--");
//                errInfo8.setErrIsFg(Vb_dp);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo8.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo8.setIsSave(0);//是否需要保存
//                errInfo8.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo8.setXh(8);//序号
//                if (ConsumeErrInfoInRedis(errInfo8, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo8, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo8);
//                    }
//                }
//
//                //电压缺相
//                ErrInfo errInfo9 = new ErrInfo();
//                errInfo9.setErrType(161);//电源中断
//                errInfo9.setNode("Vc");
//                errInfo9.setErrVal(0);
//                errInfo9.setValUin("--");
//                errInfo9.setErrIsFg(Vc_dp);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo9.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo9.setIsSave(0);//是否需要保存
//                errInfo9.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo9.setXh(9);//序号
//                if (ConsumeErrInfoInRedis(errInfo9, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo9, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo9);
//                    }
//                }
//
//                // 电压低压
//                ErrInfo errInfo10 = new ErrInfo();
//                errInfo10.setErrType(160);//欠压
//                errInfo10.setNode("Va");
//                errInfo10.setErrVal(warnValue__Va);
//                errInfo10.setValUin("V");
//                errInfo10.setErrIsFg(LVa);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo10.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo10.setIsSave(0);//是否需要保存
//                errInfo10.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo10.setXh(10);//序号
//                if (ConsumeErrInfoInRedis(errInfo10, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo10, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo10);
//                    }
//                }
//
//                // 电压低压
//                ErrInfo errInfo11 = new ErrInfo();
//                errInfo11.setErrType(160);//欠压
//                errInfo11.setNode("Vb");
//                errInfo11.setErrVal(warnValue__Vb);
//                errInfo11.setValUin("V");
//                errInfo11.setErrIsFg(LVb);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo11.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo11.setIsSave(0);//是否需要保存
//                errInfo11.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo11.setXh(11);//序号
//                if (ConsumeErrInfoInRedis(errInfo11, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo11, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo11);
//                    }
//                }
//
//                // 电压低压
//                ErrInfo errInfo12 = new ErrInfo();
//                errInfo12.setErrType(160);//欠压
//                errInfo12.setNode("Vc");
//                errInfo12.setErrVal(warnValue__Vc);
//                errInfo12.setValUin("V");
//                errInfo12.setErrIsFg(LVc);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo12.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo12.setIsSave(0);//是否需要保存
//                errInfo12.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo12.setXh(12);//序号
//                if (ConsumeErrInfoInRedis(errInfo12, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo12, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo12);
//                    }
//                }
//
//                // 电压过压
//                ErrInfo errInfo13 = new ErrInfo();
//                errInfo13.setErrType(159);//过压
//                errInfo13.setNode("Va");
//                errInfo13.setErrVal(warnValue__Va);
//                errInfo13.setValUin("V");
//                errInfo13.setErrIsFg(HVa);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo13.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo13.setIsSave(0);//是否需要保存
//                errInfo13.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo13.setXh(13);//序号
//                if (ConsumeErrInfoInRedis(errInfo13, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo13, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo13);
//                    }
//                }
//
//                // 电压过压
//                ErrInfo errInfo14 = new ErrInfo();
//                errInfo14.setErrType(159);//过压
//                errInfo14.setNode("Vb");
//                errInfo14.setErrVal(warnValue__Vb);
//                errInfo14.setValUin("V");
//                errInfo14.setErrIsFg(HVb);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo14.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo14.setIsSave(0);//是否需要保存
//                errInfo14.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo14.setXh(14);//序号
//                if (ConsumeErrInfoInRedis(errInfo14, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo14, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo14);
//                    }
//                }
//
//                // 电压过压
//                ErrInfo errInfo15 = new ErrInfo();
//                errInfo15.setErrType(159);//过压
//                errInfo15.setNode("Vc");
//                errInfo15.setErrVal(warnValue__Vc);
//                errInfo15.setValUin("V");
//                errInfo15.setErrIsFg(HVc);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo15.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo15.setIsSave(0);//是否需要保存
//                errInfo15.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo15.setXh(15);//序号
//                if (ConsumeErrInfoInRedis(errInfo15, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo15, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo15);
//                    }
//                }
//
//                // 漏电
//                ErrInfo errInfo16 = new ErrInfo();
//                errInfo16.setErrType(5);//漏电过大
//                errInfo16.setNode("L1");
//                errInfo16.setErrVal(warnValue__L);
//                errInfo16.setValUin("mA");
//                errInfo16.setErrIsFg(L);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo16.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo16.setIsSave(0);//是否需要保存
//                errInfo16.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo16.setXh(16);//序号
//                if (ConsumeErrInfoInRedis(errInfo16, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo16, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo16);
//                    }
//                }
//
//                // 温度过高
//                ErrInfo errInfo17 = new ErrInfo();
//                errInfo17.setErrType(6);//温度过高
//                errInfo17.setNode("T1");
//                errInfo17.setErrVal(warnValue__T1);
//                errInfo17.setValUin("℃");
//                errInfo17.setErrIsFg(T1);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo17.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo17.setIsSave(0);//是否需要保存
//                errInfo17.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo17.setXh(17);//序号
//                if (ConsumeErrInfoInRedis(errInfo17, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo17, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo17);
//                    }
//                }
//
//                // 温度过高
//                ErrInfo errInfo18 = new ErrInfo();
//                errInfo18.setErrType(6);//温度过高
//                errInfo18.setNode("T2");
//                errInfo18.setErrVal(warnValue__T2);
//                errInfo18.setValUin("℃");
//                errInfo18.setErrIsFg(T2);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo18.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo18.setIsSave(0);//是否需要保存
//                errInfo18.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo18.setXh(18);//序号
//                if (ConsumeErrInfoInRedis(errInfo18, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo18, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo18);
//                    }
//                }
//
//                // 温度过高
//                ErrInfo errInfo19 = new ErrInfo();
//                errInfo19.setErrType(6);//温度过高
//                errInfo19.setNode("T3");
//                errInfo19.setErrVal(warnValue__T3);
//                errInfo19.setValUin("℃");
//                errInfo19.setErrIsFg(T3);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo19.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo19.setIsSave(0);//是否需要保存
//                errInfo19.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo19.setXh(19);//序号
//                if (ConsumeErrInfoInRedis(errInfo19, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo19, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo19);
//                    }
//                }
//
//                // 温度过高
//                ErrInfo errInfo20 = new ErrInfo();
//                errInfo20.setErrType(6);//温度过高
//                errInfo20.setNode("T4");
//                errInfo20.setErrVal(warnValue__Tn);
//                errInfo20.setValUin("℃");
//                errInfo20.setErrIsFg(Tn);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo20.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo20.setIsSave(0);//是否需要保存
//                errInfo20.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo20.setXh(20);//序号
//                if (ConsumeErrInfoInRedis(errInfo20, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo20, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo20);
//                    }
//                }
//
//                // 漏电故障
//                ErrInfo errInfo21 = new ErrInfo();
//                errInfo21.setErrType(4);//传感器短路、断路
//                errInfo21.setNode("L1");
//                errInfo21.setErrVal(0);
//                errInfo21.setValUin("--");
//                errInfo21.setErrIsFg(FAULT_L);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo21.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo21.setIsSave(0);//是否需要保存
//                errInfo21.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo21.setXh(21);//序号
//                if (ConsumeErrInfoInRedis(errInfo21, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo21, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo21);
//                    }
//                }
//
//                // 温度过高故障
//                ErrInfo errInfo22 = new ErrInfo();
//                errInfo22.setErrType(4);//传感器短路、断路
//                errInfo22.setNode("T1");
//                errInfo22.setErrVal(0);
//                errInfo22.setValUin("--");
//                errInfo22.setErrIsFg(FAULT_T1);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo22.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo22.setIsSave(0);//是否需要保存
//                errInfo22.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo22.setXh(22);//序号
//                if (ConsumeErrInfoInRedis(errInfo22, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo22, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo22);
//                    }
//                }
//
//                // 温度过高故障
//                ErrInfo errInfo23 = new ErrInfo();
//                errInfo23.setErrType(4);//传感器短路、断路
//                errInfo23.setNode("T2");
//                errInfo23.setErrVal(0);
//                errInfo23.setValUin("--");
//                errInfo23.setErrIsFg(FAULT_T2);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo23.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo23.setIsSave(0);//是否需要保存
//                errInfo23.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo23.setXh(23);//序号
//                if (ConsumeErrInfoInRedis(errInfo23, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo23, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo23);
//                    }
//                }
//
//                // 温度过高故障
//                ErrInfo errInfo24 = new ErrInfo();
//                errInfo24.setErrType(4);//传感器短路、断路
//                errInfo24.setNode("T3");
//                errInfo24.setErrVal(0);
//                errInfo24.setValUin("--");
//                errInfo24.setErrIsFg(FAULT_T3);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo24.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo24.setIsSave(0);//是否需要保存
//                errInfo24.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo24.setXh(24);//序号
//                if (ConsumeErrInfoInRedis(errInfo24, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo24, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo24);
//                    }
//                }
//
//                // 温度过高故障
//                ErrInfo errInfo25 = new ErrInfo();
//                errInfo25.setErrType(4);//传感器短路、断路
//                errInfo25.setNode("T4");
//                errInfo25.setErrVal(0);
//                errInfo25.setValUin("--");
//                errInfo25.setErrIsFg(FAULT_Tn);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo25.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo25.setIsSave(0);//是否需要保存
//                errInfo25.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo25.setXh(25);//序号
//                if (ConsumeErrInfoInRedis(errInfo25, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo25, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo25);
//                    }
//                }
//
//                // 电流互感穿心方向
//                ErrInfo errInfo26 = new ErrInfo();
//                errInfo26.setErrType(132);//电流互感穿心方向
//                errInfo26.setNode("Ia");
//                errInfo26.setErrVal(0);
//                errInfo26.setValUin("--");
//                errInfo26.setErrIsFg(eleA_mi);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo26.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo26.setIsSave(0);//是否需要保存
//                errInfo26.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo26.setXh(26);//序号
//                if (ConsumeErrInfoInRedis(errInfo26, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo26, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo26);
//                    }
//                }
//
//                // 电流互感穿心方向
//                ErrInfo errInfo27 = new ErrInfo();
//                errInfo27.setErrType(132);//电流互感穿心方向
//                errInfo27.setNode("Ib");
//                errInfo27.setErrVal(0);
//                errInfo27.setValUin("--");
//                errInfo27.setErrIsFg(eleB_mi);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo27.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo27.setIsSave(0);//是否需要保存
//                errInfo27.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo27.setXh(27);//序号
//                if (ConsumeErrInfoInRedis(errInfo27, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo27, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo27);
//                    }
//                }
//
//                // 电流互感穿心方向
//                ErrInfo errInfo28 = new ErrInfo();
//                errInfo28.setErrType(132);//电流互感穿心方向
//                errInfo28.setNode("Ic");
//                errInfo28.setErrVal(0);
//                errInfo28.setValUin("--");
//                errInfo28.setErrIsFg(eleC_mi);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo28.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo28.setIsSave(0);//是否需要保存
//                errInfo28.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo28.setXh(28);//序号
//                if (ConsumeErrInfoInRedis(errInfo28, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo28, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo28);
//                    }
//                }
//
//                // 电弧报警
//                ErrInfo errInfo29 = new ErrInfo();
//                errInfo29.setErrType(7);//电弧报警
//                errInfo29.setNode("A1");
//                errInfo29.setErrVal(warn_arcA);
//                errInfo29.setValUin("个");
//                errInfo29.setErrIsFg(FAULT_arcA);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo29.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo29.setIsSave(0);//是否需要保存
//                errInfo29.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo29.setXh(29);//序号
//                if (ConsumeErrInfoInRedis(errInfo29, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo29, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo29);
//                    }
//                }
//
//                // 电弧报警
//                ErrInfo errInfo30 = new ErrInfo();
//                errInfo30.setErrType(7);//电弧报警
//                errInfo30.setNode("A2");
//                errInfo30.setErrVal(warn_arcB);
//                errInfo30.setValUin("个");
//                errInfo30.setErrIsFg(FAULT_arcB);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo30.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo30.setIsSave(0);//是否需要保存
//                errInfo30.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo30.setXh(30);//序号
//                if (ConsumeErrInfoInRedis(errInfo30, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo30, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo30);
//                    }
//                }
//
//                // 电弧报警
//                ErrInfo errInfo31 = new ErrInfo();
//                errInfo31.setErrType(7);//电弧报警
//                errInfo31.setNode("A3");
//                errInfo31.setErrVal(warn_arcC);
//                errInfo31.setValUin("个");
//                errInfo31.setErrIsFg(FAULT_arcC);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo31.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo31.setIsSave(0);//是否需要保存
//                errInfo31.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo31.setXh(31);//序号
//                if (ConsumeErrInfoInRedis(errInfo31, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo31, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo31);
//                    }
//                }
//
//                //3相电压平衡度
//                ErrInfo errInfo32 = new ErrInfo();
//                errInfo32.setErrType(133);//电压不平衡
//                errInfo32.setNode("BV");
//                errInfo32.setErrVal(warn_vb);
//                errInfo32.setValUin("%");
//                errInfo32.setErrIsFg(vb);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo32.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo32.setIsSave(0);//是否需要保存
//                errInfo32.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo32.setXh(32);//序号
//                if (ConsumeErrInfoInRedis(errInfo32, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo32, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo32);
//                    }
//                }
//
//                //3相电流平衡度
//                ErrInfo errInfo33 = new ErrInfo();
//                errInfo33.setErrType(134);//电流不平衡
//                errInfo33.setNode("BI");
//                errInfo33.setErrVal(warn_cb);
//                errInfo33.setValUin("%");
//                errInfo33.setErrIsFg(cb);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo33.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo33.setIsSave(0);//是否需要保存
//                errInfo33.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo33.setXh(33);//序号
//                if (ConsumeErrInfoInRedis(errInfo33, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo33, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo33);
//                    }
//                }
//
//                //大功率负载
//                ErrInfo errInfo34 = new ErrInfo();
//                errInfo34.setErrType(145);//大功率负载报警
//                errInfo34.setNode("MXP");
//                errInfo34.setErrVal(warn_MXP);
//                errInfo34.setValUin("W");
//                errInfo34.setErrIsFg(MXP);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo34.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo34.setIsSave(0);//是否需要保存
//                errInfo34.setSaveType(2);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo34.setXh(34);//序号
//                if (ConsumeErrInfoInRedis(errInfo34, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo34, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo34);
//                    }
//                }
//
//                //电压短路
//                ErrInfo errInfo35 = new ErrInfo();
//                errInfo35.setErrType(144);//电压短路
//                errInfo35.setNode("Va");
//                errInfo35.setErrVal(0);
//                errInfo35.setValUin("--");
//                errInfo35.setErrIsFg(SO_Va);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo35.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo35.setIsSave(0);//是否需要保存
//                errInfo35.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo35.setXh(35);//序号
//                if (ConsumeErrInfoInRedis(errInfo35, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo35, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo35);
//                    }
//                }
//
//                //电压短路
//                ErrInfo errInfo36 = new ErrInfo();
//                errInfo36.setErrType(144);//电压短路
//                errInfo36.setNode("Vb");
//                errInfo36.setErrVal(0);
//                errInfo36.setValUin("--");
//                errInfo36.setErrIsFg(SO_Vb);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo36.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo36.setIsSave(0);//是否需要保存
//                errInfo36.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo36.setXh(36);//序号
//                if (ConsumeErrInfoInRedis(errInfo36, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo36, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo36);
//                    }
//                }
//
//                //电压短路
//                ErrInfo errInfo37 = new ErrInfo();
//                errInfo37.setErrType(144);//电压短路
//                errInfo37.setNode("Vc");
//                errInfo37.setErrVal(0);
//                errInfo37.setValUin("--");
//                errInfo37.setErrIsFg(SO_Vc);//故障或者报警是恢复还是产生  0 恢复 1产生
//                errInfo37.setTime(systemTime);//故障，报警，操作时间或者恢复时间
//                errInfo37.setIsSave(0);//是否需要保存
//                errInfo37.setSaveType(3);//3 Fault 故障 2 HiddenTrouble 隐患
//                errInfo37.setXh(37);//序号
//                if (ConsumeErrInfoInRedis(errInfo37, baseData.getDevsignature(), baseData.getDeviceId(), errinfos)) {
//                    if (ExecuteSql(errInfo37, baseData.getDevsignature(), 0)) {
//                        list.add(errInfo37);
//                    }
//                }
//
//               // "L1|T1|T2|T3|T4|Ia|Ib|Ic|Va|Vb|Vc|QA|QB|QC|PA|PB|PC|DA|DB|BC|FA|FB|FC|A1|A2|A3|BV|BI|CSQ|VAB|VBC|VCA|IAB|IBC|ICA|MXP"
//                // 实时值
//                StringBuffer sb = new StringBuffer();
//                sb.append(value_L).append("|").append(value_T1).append("|").append(value_T2).append("|").append(value_T3).append("|")
//                        .append(value_Tn).append("|").append(value_Ia).append("|").append(value_Ib).append("|").append(value_Ic).append("|")
//                        .append(value__Va).append("|").append(value__Vb).append("|").append(value__Vc).append("|").append(Qa).append("|")
//                        .append(Qb).append("|").append(Qc).append("|").append(apA).append("|").append(apB).append("|").append(apC).append("|")
//                        .append(pfA).append("|").append(pfB).append("|").append(pfC).append("|").append(Fa).append("|").append(Fb).append("|")
//                        .append(Fc).append("|").append(value_arcA).append("|").append(value_arcB).append("|").append(value_arcC).append("|")
//                        .append(value_vb).append("|").append(value_cb).append("|").append(CSQ).append("|").append(Vab).append("|").append(Vbc).append("|")
//                        .append(Vca).append("|").append(Iab).append("|").append(Ibc).append("|").append(Ica).append("|").append(warn_MXP);
//
//                String value = sb.toString();
//
//                logger.info("智慧用电数据上报--value：" + value);
//
//                // 流程化处理，设备控制结果返回
//                processizeCommand(baseData.getDeviceId(), "", 0);
//
//                // 计算用电量
////                PushEleConsumptionInRedis(baseData.getDeviceIdPk(), Qa + Qb + Qc, baseData.getEventTime(), 3);
//                PushEleConsumptionInRedis_v20191029(baseData.getDeviceIdPk(), (int) ((Qa + Qb + Qc) * 10), baseData.getEventTime(), 10);
//
//                if (list.size() == 0) {
//                    //写入数据库和hbase
//                    PostInHBase(baseData.getDeviceIdPk(), value, _habase_desc);
//                }
//                else {
//                    PushMessageInRedis(list, baseData, systemTime);
//                    //写入数据库和hbase
//                    PostInHBase(baseData.getDeviceIdPk(), value, _habase_desc);
//                }
//
//                return true;
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                return false;
//            }
//        }
//
//        /**
//         * 读取参数
//         * @param data
//         * @param baseData
//         * @return
//         */
//        private boolean deviceParamsAnalysis(String data, BaseData baseData) {
//            if (null == data){
//                return false;
//            }
//            try {
//
//                Web_WisdomElectricity we = new Web_WisdomElectricity();
//                int[] intData = BytesHexStrTranslate.doubleHexStrToIntArray(data);
//
//                int relay = intData[0] > 0 ? 1 : 0; // 继电器 0 无动作 1有动作
//                int buzzer = intData[1] > 0 ? 1 : 0; // 蜂鸣器 0 不响   1响
//                int VT = intData[2] > 0 ? 1: 0; //电压类型 0单相   1三相
//                int Pt = intData[3]; //电压变比
//                int ecvrA = intData[4]; //第一路A相电流 变比
//                int ecvrB = intData[5]; //第二路B相电流 变比
//                int ecvrC = intData[6]; //第三路C相电流 变比
//                int adv = intData[7]; // 报警延时值 Alarm delay value
//
//                we.setRelay(relay);
//                we.setBuzzer(buzzer);
//                we.setVT(VT);
//                we.setPt(Pt);
//                we.setEcvrA(ecvrA);
//                we.setEcvrB(ecvrB);
//                we.setEcvrC(ecvrC);
//                we.setAdv(adv);
//
//                // 转成2进制数
//                String warnStatus = Integer.toBinaryString(intData[8]);
//                // 左补0
//                warnStatus = StringUtil.PadLeft(warnStatus,16);
//
//                int[] bstatus = new int[16];
//                for (int i = 0; i < 16; i++) {
//                    bstatus[i] = Integer.parseInt(warnStatus.substring(i,i+1));
//                }
//
//                // 是否接传感器标志位  0 没接 1接
//                Integer L1 = bstatus[15]; // 第一路漏电
//                Integer T1 = bstatus[14]; // 第一路A相温度
//                Integer T2 = bstatus[13]; // 第二路B相温度
//                Integer T3 = bstatus[12]; // 第三路C相温度
//                Integer T4 = bstatus[11]; // 第四路零相温度
//                Integer Ia = bstatus[10]; // 第一路A相电流
//                Integer Ib = bstatus[9]; // 第二路B相电流
//                Integer Ic = bstatus[8]; // 第三路C相电流
//                Integer Va = bstatus[7]; // 第一路A相电压
//                Integer Vb = bstatus[6]; // 第二路B相电压
//                Integer Vc = bstatus[5]; // 第三路C相电压
//
//                we.setL1(L1);
//                we.setT1(T1);
//                we.setT2(T2);
//                we.setT3(T3);
//                we.setT4(T4);
//                we.setIa(Ia);
//                we.setIb(Ib);
//                we.setIc(Ic);
//                we.setVa(Va);
//                we.setVb(Vb);
//                we.setVc(Vc);
//
//                // 报警设置值
//                int v_L1 = intData[9]; // 漏电
//                int v_T1 = intData[10]; // A相温度
//                int v_T2 = intData[11]; // B相温度
//                int v_T3 = intData[12]; // C相温度
//                int v_T4 = intData[13]; // D相温度
//                int v_Ia = intData[14]; // A相电流
//                int v_Ib = intData[15]; // B相电流
//                int v_Ic = intData[16]; // C相电流
//                int v_Va = intData[17]; // A相电压
//                int v_Vb = intData[18]; // B相电压
//                int v_Vc = intData[19]; // C相电压
//
//                we.setV_L1(v_L1);
//                we.setV_T1(v_T1);
//                we.setV_T2(v_T2);
//                we.setV_T3(v_T3);
//                we.setV_T4(v_T4);
//                we.setV_Ia(v_Ia);
//                we.setV_Ib(v_Ib);
//                we.setV_Ic(v_Ic);
//                we.setV_Va(v_Va);
//                we.setV_Vb(v_Vb);
//                we.setV_Vc(v_Vc);
//
//                // 报警设置值的百分数  ratio
//                int r_L = intData[20]; // 漏电
//                int r_T = intData[21]; // 温度
//                int r_I = intData[22]; // 电流
//                int r_V = intData[23]; // 电压
//
//                we.setR_L(r_L);
//                we.setR_T(r_T);
//                we.setR_I(r_I);
//                we.setR_V(r_V);
//
//                int v_adv = intData[24]; // 数据上传延时时间。
//                int v_arcA = intData[25]; //A相故障电弧报警设置个数600S内1-60000个没有小数点
//                int v_arcB = intData[26]; //B相故障电弧报警设置个数600S内1-60000个没有小数点
//                int v_arcC = intData[27]; //C相故障电弧报警设置个数600S内1-60000个没有小数点
//
//                we.setV_adv(v_adv);
//                we.setV_arcA(v_arcA);
//                we.setV_arcB(v_arcB);
//                we.setV_arcC(v_arcC);
//
//                int r_arc = intData[28]; //故障电弧报警设置额定值的百分比5-100     没有小数点
//                int wt_arc = intData[29]; // 故障电弧报警设置时间 1-600S
//                double v_bv = intData[30]/10d; // 3相电压平衡度报警设置值：默认40对应4%   两个字节一个小数点
//                double v_bc = intData[31]/10d; // 3相电流平衡度报警设置值：默认40对应4%   两个字节一个小数点
//                int wt_b = intData[32]; // 3相平衡度报警设置时间：默认3S    没有小数点 degree of balance
//
//                we.setR_arc(r_arc);
//                we.setWt_arc(wt_arc);
//                we.setV_bv(v_bv);
//                we.setV_bc(v_bc);
//                we.setWt_b(wt_b);
//
//                // 转成2进制数
//                String warnStatus2 = Integer.toBinaryString(intData[33]);
//                // 左补0
//                warnStatus2 = StringUtil.PadLeft(warnStatus2,16);
//
//                int[] bstatus2 = new int[16];
//                for (int i = 0; i < 16; i++) {
//                    bstatus2[i] = Integer.parseInt(warnStatus2.substring(i,i+1));
//                }
//
//                // 是否打开关闭  0关闭 1打开
//                Integer arcA = bstatus2[15]; // A相电弧
//                Integer arcB = bstatus2[14]; // B相电弧
//                Integer arcC = bstatus2[13]; // C相电弧
//                Integer bv = bstatus2[12]; // 电压平衡度
//                Integer bc = bstatus2[11]; // 电流平衡度
//                Integer ckA = bstatus2[10]; // A相穿孔方向
//                Integer ckB = bstatus2[9]; // B相穿孔方向
//                Integer ckC = bstatus2[8]; // C相穿孔方向
//                Integer MXP = bstatus2[7]; // 大功率负载
//                Integer so_Va = bstatus2[6]; // A相电压短路
//                Integer so_Vb = bstatus2[5]; // B相电压短路
//                Integer so_Vc = bstatus2[4]; // C相电压短路
//
//                we.setArcA(arcA);
//                we.setArcB(arcB);
//                we.setArcC(arcC);
//                we.setBv(bv);
//                we.setBc(bc);
//                we.setCkA(ckA);
//                we.setCkB(ckB);
//                we.setCkC(ckC);
//                we.setMXP(MXP);
//                we.setSo_Va(so_Va);
//                we.setSo_Vb(so_Vb);
//                we.setSo_Vc(so_Vc);
//
//                int v_MXP = (intData[35] << 8) | intData[34]; // 大功率负载 没有小数点
//                int soV_desc = intData[36]; // 短路电压下降百分比默认10 没有小数点
//                int soV_asc = intData[37]; // 短路电压上升百分比默认10 没有小数点
//
//                we.setV_MXP(v_MXP);
//                we.setSoV_desc(soV_desc);
//                we.setSoV_asc(soV_asc);
//
//                int r_minV = intData[38]; // 电压低压百分比
//                int r_maxV = intData[39]; // 电压高压百分比
//                int r_olI = intData[40]; // 电流过载百分比 overload
//
//                we.setR_minV(r_minV);
//                we.setR_maxV(r_maxV);
//                we.setR_olI(r_olI);
//
//                String jsonData = JSON.toJSON(we).toString();
//                deviceParamService.AddAndUpdateProtocolParams(baseData.getDeviceIdPk(), 0, Web_staticType.Web_WisdomElectricity.type, jsonData);
//
//                // L1|T1|T2|T3|T4|Ia|Ib|Ic|Va|Vb|Vc|QA|QB|QC|PA|PB|PC|DA|DB|BC|FA|FB|FC|A1|A2|A3|BV|BI|CSQ|VAB|VBC|VCA|IAB|IBC|ICA|MXP
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "L1", v_L1, L1+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "T1", v_T1, T1+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "T2", v_T2, T2+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "T3", v_T3, T3+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "T4", v_T4, T4+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "Ia", v_Ia, Ia+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "Ib", v_Ib, Ib+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "Ic", v_Ic, Ic+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "Va", v_Va, Va+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "Vb", v_Vb, Vb+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "Vc", v_Vc, Vc+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "A1", v_arcA, arcA+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "A2", v_arcB, arcB+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "A3", v_arcC, arcC+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "BV", v_bv, bv+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "BI", v_bc, bc+"", 0);
//                deviceParamService.AddAndUpdateDeviceParams(baseData.getDeviceIdPk(), "MXP", v_MXP, MXP+"", 0);
//
//                // 流程化处理，设备控制结果返回
//                processizeCommand(baseData.getDeviceId(), jsonData, 0);
//
//                CacheRealValue.map.put(baseData.getDevsignature()+String.format("%02x", (0x80 + Integer.parseInt(baseData.getUdpPacketType(),16))),
//                        "true|" + System.currentTimeMillis() + "|" + jsonData);
//                return true;
//
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                CacheRealValue.map.put(baseData.getDevsignature()+String.format("%02x", (0x80 + Integer.parseInt(baseData.getUdpPacketType(),16))),"false|" + System.currentTimeMillis());
//                return false;
//            }
//        }
//
//    }
//
