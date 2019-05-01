//
//  ViewController.swift
//  mezamashi
//
//  Created by 石垣一樹 on 2019/04/27.
//  Copyright © 2019 石垣一樹. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let label = UILabel(frame: CGRect(x:0, y:0, width: 300, height:21))
        label.center = CGPoint(x:160, y:285)
        label.textAlignment = .center
        label.font = label.font.withSize(25)
        label.text = CommonKt.createApplicationScreenMessage()
        self.view.addSubview(label)
        
        CommonKt.callDelayed { () -> KotlinUnit in
            DispatchQueue.main.async {
                label.text = "timer end"
            }
            return .init()
        }
    }
}

