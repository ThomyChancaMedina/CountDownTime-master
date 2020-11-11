package com.thomy.library.ui


import android.content.Context
import android.os.CountDownTimer
import android.text.TextUtils
import android.util.AttributeSet
import com.thomy.library.R


class CountDownTime  @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

):androidx.appcompat.widget.AppCompatTextView(
    mContext, attrs, defStyleAttr
){

    interface TimerListener {
        fun onTick(millisUntilFinished: Long)
        fun onFinish()
    }

    private var mPrefixText: CharSequence? = null
    private var mSuffixText: CharSequence? = null
    private var mHours: Long = 0
    private var mMinutes: Long = 0
    private var mSeconds: Long = 0
    private var mMilliSeconds: Long = 0
    private var mListener: TimerListener? = null
    fun setOnTimerListener(listener: TimerListener?) {
        mListener = listener
    }

    private var mCountDownTime: CountDownTimer? = null
    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {
        val a =
            mContext.obtainStyledAttributes(attrs, R.styleable.CountDownTime, defStyleAttr, 0)
        mPrefixText = a.getString(R.styleable.CountDownTime_prefixText)
        mSuffixText = a.getString(R.styleable.CountDownTime_suffixText)
        val milliStr = a.getString(R.styleable.CountDownTime_timeMilliSeconds)
        if (!TextUtils.isEmpty(milliStr) && TextUtils.isDigitsOnly(milliStr)) {
            mMilliSeconds =
                a.getString(R.styleable.CountDownTime_timeMilliSeconds)!!.toLong()
            setTime(mMilliSeconds)
            startCountDown()
        }
        a.recycle()
        displayText()
    }

    private fun initCounter() {
        mCountDownTime = object : CountDownTimer(mMilliSeconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                calculateTime(millisUntilFinished)
                if (mListener != null) {
                    mListener!!.onTick(millisUntilFinished)
                }
            }

            override fun onFinish() {
                calculateTime(0)
                if (mListener != null) {
                    mListener!!.onFinish()
                }
            }
        }
    }

   fun startCountDown() {
        if (mCountDownTime != null) {
            mCountDownTime!!.start()
        }
    }

    fun setTime(milliSeconds: Long) {
        mMilliSeconds = milliSeconds
        initCounter()
        calculateTime(milliSeconds)
    }

    private fun calculateTime(milliSeconds: Long) {
        mSeconds = milliSeconds / 1000
        mMinutes = mSeconds / 60
        mSeconds %= 60
        mHours = mMinutes / 60
        mMinutes %= 60
        displayText()
    }

    private fun displayText() {
        val buffer = StringBuffer()
        if (!TextUtils.isEmpty(mPrefixText)) {
            buffer.append(mPrefixText)
            buffer.append(" ")
        }
        buffer.append(getTwoDigitNumber(mHours))
        buffer.append(":")
        buffer.append(getTwoDigitNumber(mMinutes))
        buffer.append(":")
        buffer.append(getTwoDigitNumber(mSeconds))
        if (!TextUtils.isEmpty(mSuffixText)) {
            buffer.append(" ")
            buffer.append(mSuffixText)
        }
        text = buffer
    }

    private fun getTwoDigitNumber(mHours: Long): Any {
        return if (mHours in 0..9) {
            "0$mHours"
        } else mHours.toString()
    }

    init {
        init(attrs, defStyleAttr)
    }

}