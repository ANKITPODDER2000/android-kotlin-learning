// Generated by view binder compiler. Do not edit!
package com.example.basicapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.basicapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnSubmit;

  @NonNull
  public final EditText etFirstName;

  @NonNull
  public final EditText etLastName;

  @NonNull
  public final EditText etLocation;

  @NonNull
  public final EditText etPhoneNumber;

  @NonNull
  public final TextView tvUserDetails;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnSubmit,
      @NonNull EditText etFirstName, @NonNull EditText etLastName, @NonNull EditText etLocation,
      @NonNull EditText etPhoneNumber, @NonNull TextView tvUserDetails) {
    this.rootView = rootView;
    this.btnSubmit = btnSubmit;
    this.etFirstName = etFirstName;
    this.etLastName = etLastName;
    this.etLocation = etLocation;
    this.etPhoneNumber = etPhoneNumber;
    this.tvUserDetails = tvUserDetails;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSubmit;
      Button btnSubmit = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmit == null) {
        break missingId;
      }

      id = R.id.etFirstName;
      EditText etFirstName = ViewBindings.findChildViewById(rootView, id);
      if (etFirstName == null) {
        break missingId;
      }

      id = R.id.etLastName;
      EditText etLastName = ViewBindings.findChildViewById(rootView, id);
      if (etLastName == null) {
        break missingId;
      }

      id = R.id.etLocation;
      EditText etLocation = ViewBindings.findChildViewById(rootView, id);
      if (etLocation == null) {
        break missingId;
      }

      id = R.id.etPhoneNumber;
      EditText etPhoneNumber = ViewBindings.findChildViewById(rootView, id);
      if (etPhoneNumber == null) {
        break missingId;
      }

      id = R.id.tvUserDetails;
      TextView tvUserDetails = ViewBindings.findChildViewById(rootView, id);
      if (tvUserDetails == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, btnSubmit, etFirstName,
          etLastName, etLocation, etPhoneNumber, tvUserDetails);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
