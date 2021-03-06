package com.zcl.hxqh.zhongchuliang.view.activity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.Po;


/**
 * 物质接收详情
 */

public class PodetailsActivity extends BaseActivity {
    private static final String TAG = "PodetailsActivity";
    /**
     * 标题
     **/
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;


    private TextView poNumText; //采购单

    private TextView poDescText; //描述

    private TextView poVendorText;//公司

    private TextView poVendornameText;//公司名称

    private TextView poShiptoattnText;//接收人

    private TextView poShiptoattnnameText;//接收人描述

    private TextView poSiteidText;//地点

    private TextView poPretaxtotalText;//税前总计

    private TextView poReceivedtotalcostText;//已接收成本

    private TextView poStatusText;//状态

    private TextView poReceiptsText;//接收

    private TextView poOrderdateText;//订购日期

    private Button recordeBtn;//接收按钮

    private Button returnBtn;//返回按钮

    private RelativeLayout loadmoreLayout;//点击加载更多
    private Button loadmoreButton;

    private LinearLayout loadLayout;//加载布局

    private final int RECORDE_MARK = 1000;//接收标记
    private final int RETURN_MARK = 1001;//退货标记

    private Po po;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podetails);
        getIntentData();
        initView();
        setEvent();
    }

    private void getIntentData() {
        po = (Po) getIntent().getSerializableExtra("po");

    }


    /**
     * 初始化界面组件
     **/
    private void initView() {
        titleText = (TextView) findViewById(R.id.txt_title);
        backImageView = (ImageView) findViewById(R.id.img_back);
        poNumText = (TextView) findViewById(R.id.po_num_text);
        poDescText = (TextView) findViewById(R.id.po_desc_text);
        poVendorText = (TextView) findViewById(R.id.po_vendor_text);
        poVendornameText = (TextView) findViewById(R.id.po_vendorname_text);
        poShiptoattnText = (TextView) findViewById(R.id.po_shiptoattn_text);
        poShiptoattnnameText = (TextView) findViewById(R.id.po_shiptoattnname_text);
        poSiteidText = (TextView) findViewById(R.id.po_siteid_text);
        poPretaxtotalText = (TextView) findViewById(R.id.po_pretaxtotal_text);
        poReceivedtotalcostText = (TextView) findViewById(R.id.po_receivedtotalcost_text);
        poStatusText = (TextView) findViewById(R.id.po_status_text);
        poReceiptsText = (TextView) findViewById(R.id.po_receipts_text);
        poOrderdateText = (TextView) findViewById(R.id.po_orderdate_text);

        loadmoreLayout = (RelativeLayout) findViewById(R.id.load_more_layout);
        loadmoreButton = (Button) findViewById(R.id.load_more_button);
        loadLayout = (LinearLayout) findViewById(R.id.load_layout);

        recordeBtn = (Button) findViewById(R.id.po_recorde_text);
        returnBtn = (Button) findViewById(R.id.po_return_text);
    }

    /**
     * 设置事件监听
     **/
    private void setEvent() {
        titleText.setText(getString(R.string.material_receiving));
        backImageView.setVisibility(View.VISIBLE);
        backImageView.setOnClickListener(backImageViewOnClickListener);
        if (po != null) {
            poNumText.setText(po.ponum);
            poDescText.setText(po.description);
            poVendorText.setText(po.vendor);
            poVendornameText.setText(po.vendorname);
            poShiptoattnText.setText(po.shiptoattn);
            poShiptoattnnameText.setText(po.shiptoattnname);
            poSiteidText.setText(po.siteid);
            poPretaxtotalText.setText(po.pretaxtotal);
            poReceivedtotalcostText.setText(po.receivedtotalcost);
            poStatusText.setText(po.status);
            poReceiptsText.setText(po.receipts);
            poOrderdateText.setText(po.orderdate);
        }
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);
        loadmoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadLayout.getVisibility()!=View.VISIBLE) {
                    loadLayout.setVisibility(View.VISIBLE);
                    loadmoreButton.setBackgroundResource(R.drawable.up);
                }else {
                    loadLayout.setVisibility(View.GONE);
                    loadmoreButton.setBackgroundResource(R.drawable.down);
                }
            }
        });

        recordeBtn.setOnClickListener(intentOnClicListener);
        returnBtn.setOnClickListener(intentOnClicListener);
    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener intentOnClicListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PodetailsActivity.this, PoLineActivity.class);
            intent.putExtra("ponum",po.ponum);
            if(v.getId()==recordeBtn.getId()){
                intent.putExtra("mark",RECORDE_MARK);
            }else if(v.getId()==returnBtn.getId()){
                intent.putExtra("mark",RETURN_MARK);
            }
            startActivity(intent);
        }
    };

}
