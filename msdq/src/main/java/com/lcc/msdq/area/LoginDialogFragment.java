package com.lcc.msdq.area;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.lcc.App;
import com.lcc.db.test.UserInfo;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.MainActivity;
import com.lcc.msdq.R;
import com.lcc.msdq.choice.ChoiceTypeoneActivity;
import com.lcc.mvp.presenter.LoginPresenter;
import com.lcc.mvp.presenter.impl.LoginPresenterImpl;
import com.lcc.mvp.view.LoginView;
import com.lcc.utils.FormValidation;
import com.lcc.utils.KeyboardUtils;
import com.lcc.utils.WidgetUtils;
import com.lcc.view.CityPicker;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.view.simplearcloader.SimpleArcDialog;

public class LoginDialogFragment extends DialogFragment implements LoginView, View.OnClickListener {
	//MVP结构的 P层的实现
	private LoginPresenter mPresenter;
	//用户名和密码
	private TextInputLayout mTextInputLayoutPhone;
	private TextInputLayout mTextInputLayoutPassword;
	//简单的登录时候的等待的对话框
	private SimpleArcDialog mDialog;
	private String from;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.login_layout, null);
		initView(view);
		builder.setView(view);
		return builder.create();
	}

	private void initView(View view){
		Button mButtonSign = (Button) view.findViewById(R.id.button_sign);
		if (mButtonSign != null) {
			mButtonSign.setOnClickListener(this);
		}
		mPresenter = new LoginPresenterImpl(this);
		view.findViewById(R.id.textView_create_account).setOnClickListener(this);
		view.findViewById(R.id.textView_reset_password).setOnClickListener(this);
		mTextInputLayoutPhone = (TextInputLayout) view.findViewById(R.id.textInputLayout_phone);
		mTextInputLayoutPassword = (TextInputLayout) view.findViewById(R.id.textInputLayout_password);
	}

	/**
	 * 对用户名和密码的基本的校验
	 */
	public boolean valid(String phone, String password) {
		if (!FormValidation.isMobile(phone)) {
			WidgetUtils.requestFocus(mTextInputLayoutPhone.getEditText());
			FrameManager.getInstance().toastPrompt(getResources().getString( R.string.msg_error_phone));
			return true;
		}
		if (!FormValidation.isSimplePassword(password)) {
			WidgetUtils.requestFocus(mTextInputLayoutPassword.getEditText());
			FrameManager.getInstance().toastPrompt(getResources().getString( R.string.msg_error_password));
			return true;
		}
		return false;
	}

	/**
	 * 进行基本的校验，成功的话就登录
	 */
	public void login() {
		KeyboardUtils.hide(getActivity());
		String phone = mTextInputLayoutPhone.getEditText().getText().toString();
		String password = mTextInputLayoutPassword.getEditText().getText().toString();
		if (valid(phone, password))
			return;
		mPresenter.login(phone, password);
	}

	@Override
	public void showLoading() {

	}

	@Override
	public void showLoginFail(String msg) {
		FrameManager.getInstance().toastPrompt("登录失败" + msg);
		dismiss();
	}

	@Override
	public void loginSuccess() {
		FrameManager.getInstance().toastPrompt("登录成功");
		dismiss();
	}

	@Override
	public void onClick(View v) {

	}

	public interface StringListener {
		void onStringInputComplete(String message);
	}

	public interface CodeListener {
		void onCodeInputComplete(String message);
	}

}
