package com.fugenapp.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.fugenapp.FugenApp;
import com.fugenapp.R;
import com.fugenapp.adapters.EventFragmentsAdapter;
import com.fugenapp.interfaces.OnEventSelectedListener;
import com.fugenapp.ui.fragments.SearchFragment;
import com.fugenapp.ui.view.KeyboardSensitiveRelativeLayout;
import com.fugenapp.ui.view.LessAnnoyingFrameLayout;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, OnEventSelectedListener, KeyboardSensitiveRelativeLayout.OnKeyboardShowHideListener, PopupMenu.OnMenuItemClickListener {

    public static final int VOICE_REQUEST_CODE = 2304;

    private TextView flagshipBtn;
    private TextView eyeCatcherBtn;
    private TextView technicalBtn;
    private TextView funBtn;
    private EditText searchField;
    private ViewPager fragmentPager;
    private HorizontalScrollView scrollView;
    private View searchFragContainer;
    private ImageView voiceButton;
    private LessAnnoyingFrameLayout touchInterceptor;

    private SearchFragment searchFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        searchFragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.search_frag_container, searchFragment).addToBackStack("search").commit();
        searchField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    searchFragContainer.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.search_frag_container, searchFragment).addToBackStack("search   ").commit();
                } else {
                    if (searchField.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).remove(searchFragment).commit();
                    }
                }
            }
        });

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchFragment.filter(editable.toString());
            }
        });

        ((KeyboardSensitiveRelativeLayout) findViewById(R.id.main_act_container)).setKeyboardListener(this);

        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (searchField.isFocused()) {
                        Rect outRect = new Rect();
                        searchField.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                            searchField.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            assert imm != null;
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

        if (activities.size() == 0) {
            voiceButton.setEnabled(false);
        }

        fragmentPager.setAdapter(new EventFragmentsAdapter(getSupportFragmentManager()));
        fragmentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                refreshCategoryButtons(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void findViews() {
        fragmentPager = findViewById(R.id.fragment_pager);
        flagshipBtn = findViewById(R.id.flagship_btn);
        eyeCatcherBtn = findViewById(R.id.eye_catcher_btn);
        technicalBtn = findViewById(R.id.technical_btn);
        funBtn = findViewById(R.id.fun_btn);
        scrollView = findViewById(R.id.category_tabs);
        searchField = findViewById(R.id.search_field);
        searchFragContainer = findViewById(R.id.search_frag_container);
        touchInterceptor = findViewById(R.id.touchInterceptor);
        voiceButton = findViewById(R.id.voice_btn);
    }

    private void refreshCategoryButtons(int position) {
        int attributeResourceId = FugenApp.getResIdFromAttribute(this, R.attr.defaultCardBG);
        Drawable defaultBG = getResources().getDrawable(attributeResourceId);
        attributeResourceId = FugenApp.getResIdFromAttribute(this, R.attr.selectedCardBG);
        Drawable selectedBG = getResources().getDrawable(attributeResourceId);
        int defaultTextResID = FugenApp.getResIdFromAttribute(this, R.attr.defaultTextColor);
        int selectedTextResID = FugenApp.getResIdFromAttribute(this, R.attr.selectedItemTextColor);

        switch (position) {
            case 0:
                flagshipBtn.setBackground(selectedBG);
                eyeCatcherBtn.setBackground(defaultBG);
                technicalBtn.setBackground(defaultBG);
                funBtn.setBackground(defaultBG);
                flagshipBtn.setTextColor(getResources().getColor(selectedTextResID));
                eyeCatcherBtn.setTextColor(getResources().getColor(defaultTextResID));
                technicalBtn.setTextColor(getResources().getColor(defaultTextResID));
                funBtn.setTextColor(getResources().getColor(defaultTextResID));

                scrollToView(scrollView, flagshipBtn);
                break;
            case 1:
                flagshipBtn.setBackground(defaultBG);
                eyeCatcherBtn.setBackground(selectedBG);
                technicalBtn.setBackground(defaultBG);
                funBtn.setBackground(defaultBG);
                flagshipBtn.setTextColor(getResources().getColor(defaultTextResID));
                eyeCatcherBtn.setTextColor(getResources().getColor(selectedTextResID));
                technicalBtn.setTextColor(getResources().getColor(defaultTextResID));
                funBtn.setTextColor(getResources().getColor(defaultTextResID));

                scrollToView(scrollView, eyeCatcherBtn);
                break;
            case 2:
                flagshipBtn.setBackground(defaultBG);
                eyeCatcherBtn.setBackground(defaultBG);
                technicalBtn.setBackground(selectedBG);
                funBtn.setBackground(defaultBG);
                flagshipBtn.setTextColor(getResources().getColor(defaultTextResID));
                eyeCatcherBtn.setTextColor(getResources().getColor(defaultTextResID));
                technicalBtn.setTextColor(getResources().getColor(selectedTextResID));
                funBtn.setTextColor(getResources().getColor(defaultTextResID));

                scrollToView(scrollView, technicalBtn);
                break;
            case 3:
                flagshipBtn.setBackground(defaultBG);
                eyeCatcherBtn.setBackground(defaultBG);
                technicalBtn.setBackground(defaultBG);
                funBtn.setBackground(selectedBG);
                flagshipBtn.setTextColor(getResources().getColor(defaultTextResID));
                eyeCatcherBtn.setTextColor(getResources().getColor(defaultTextResID));
                technicalBtn.setTextColor(getResources().getColor(defaultTextResID));
                funBtn.setTextColor(getResources().getColor(selectedTextResID));

                scrollToView(scrollView, funBtn);
                break;
        }
    }

    /**
     * Used to scroll to the given view.
     *
     * @param scrollViewParent Parent ScrollView
     * @param view             View to which we need to scroll.
     */
    private void scrollToView(final HorizontalScrollView scrollViewParent, final View view) {
        // Get deepChild Offset
        Point childOffset = new Point();
        getDeepChildOffset(scrollViewParent, view.getParent(), view, childOffset);
        // Scroll to child.
        scrollViewParent.smoothScrollTo(childOffset.x, 0);
    }

    /**
     * Used to get deep child offset.
     * <p/>
     * 1. We need to scroll to child in scrollview, but the child may not the direct child to scrollview.
     * 2. So to get correct child position to scroll, we need to iterate through all of its parent views till the main parent.
     *
     * @param mainParent        Main Top parent.
     * @param parent            Parent.
     * @param child             Child.
     * @param accumulatedOffset Accumalated Offset.
     */
    private void getDeepChildOffset(final ViewGroup mainParent, final ViewParent parent, final View child, final Point accumulatedOffset) {
        ViewGroup parentGroup = (ViewGroup) parent;
        accumulatedOffset.x += child.getLeft();
        accumulatedOffset.y += child.getTop();
        if (parentGroup.equals(mainParent)) {
            return;
        }
        getDeepChildOffset(mainParent, parentGroup.getParent(), parentGroup, accumulatedOffset);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item0:
                fragmentPager.setCurrentItem(0, true);
                refreshCategoryButtons(0);
                break;
            case R.id.item1:
                fragmentPager.setCurrentItem(1, true);
                refreshCategoryButtons(1);
                break;
            case R.id.item2:
                fragmentPager.setCurrentItem(2, true);
                refreshCategoryButtons(2);
                break;
            case R.id.item3:
                fragmentPager.setCurrentItem(3, true);
                refreshCategoryButtons(3);
                break;
            case R.id.voice_btn:
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something...");
                startActivityForResult(intent, VOICE_REQUEST_CODE);
                break;
            case R.id.search_icon:
                searchField.requestFocus();
                break;
        }
    }

    public void showPopupMenu(View v) {
        PopupMenu menu = new PopupMenu(this, v);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
        menu.show();
    }

    @Override
    public void onEventSelected(int resID) {
        //Event popup shown
    }

    @Override
    public void onEventDeselected() {
        //Event popup not shown
    }

    @Override
    public void onKeyboardShowHide(boolean visible) {
        if (!visible) {
            searchField.clearFocus();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String word = matches.get(0);
            for (String match : matches) {
                if (searchFragment.canFilter(match)) {
                    word = match;
                    break;
                }
            }
            searchField.setText(word);
            searchField.requestFocus();
            searchFragment.filter(word);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (!searchField.getText().toString().equalsIgnoreCase("")) {
            searchField.setText("");
            searchField.clearFocus();
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).remove(searchFragment).commit();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.about:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            default:
                return false;
        }
    }
}