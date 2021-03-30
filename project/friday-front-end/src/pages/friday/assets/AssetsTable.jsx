import React from 'react';
import { Table, Input, InputNumber, Popconfirm, Form, Col, Row, DatePicker,Descriptions, Badge } from 'antd';
import Button from "antd/es/button";


export default class AssetsTable extends React.Component{
  constructor(props){
    super(props)
    this.state = {
      assetsId:'',
      assetsName:'',
      assetsLocation:'',
      assetsCreateTime:'',
      totalPrice:'',
      historicalValue:'',
      assetsOwner:'',
      assetsInstalment:'',
      instalmentPrice:'',
      instalmentSurplus:'',
      realizationValue:'',
      assetsRemark:'',
      allPrices:'',
      lastMonth:'',
      profitAndLoss:'',
      profitAndLossPer:'',
      incomePer:''
    }
  }
  render() {
    return(
     <div style={{background:'#ffffff',padding:'5%'}}>
       <Descriptions title="奥迪A4L" layout="vertical" bordered>
         <Descriptions.Item label="资产id">8</Descriptions.Item>
         <Descriptions.Item label="资产名">奥迪A4L 2020款35TFSI时尚动感型</Descriptions.Item>
         <Descriptions.Item label="所在地">成都</Descriptions.Item>
         <Descriptions.Item label="获得时间">2020-02-24 18:00:00</Descriptions.Item>
         <Descriptions.Item label="初期花费">105800</Descriptions.Item>
         <Descriptions.Item label="历史价值">305800</Descriptions.Item>
         <Descriptions.Item label="变现价值">259000</Descriptions.Item>
         <Descriptions.Item label="状态">
           <Badge status="processing" text="使用中" />
         </Descriptions.Item>
         <Descriptions.Item label="所有者">Ezer_Wu</Descriptions.Item>
         <Descriptions.Item label="资产分期">3*12</Descriptions.Item>
         <Descriptions.Item label="每期价格">5800</Descriptions.Item>
         <Descriptions.Item label="剩余期限（年）">3</Descriptions.Item>
         <Descriptions.Item label="资产总价">{parseInt((5800*36+105800)*100)}%</Descriptions.Item>
         <Descriptions.Item label="剩余期数（月）">35</Descriptions.Item>
         <Descriptions.Item label="月供占收入比">{5800/10000}</Descriptions.Item>
         <Descriptions.Item label="变现盈亏">{259000-305800}</Descriptions.Item>
         <Descriptions.Item label="盈亏率">{parseInt(((259000-305800)/305800)*100)}%</Descriptions.Item>
         <Descriptions.Item label=""></Descriptions.Item>
         <Descriptions.Item label="备注"  span={3}>
           Data disk type: MongoDB
           <br />
           Database version: 3.4
           <br />
           Package: dds.mongo.mid
           <br />
           Storage space: 10 GB
           <br />
           Replication factor: 3
           <br />
           Region: East China 1<br />
         </Descriptions.Item>
       </Descriptions>
     </div>
    )
  }
}
