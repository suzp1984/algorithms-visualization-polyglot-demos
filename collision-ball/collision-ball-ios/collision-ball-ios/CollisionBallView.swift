//
//  CollisionBallView.swift
//  collision-ball-ios
//
//  Created by Jacob Su on 10/5/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import UIKit

class CollisionBallView: UIView {

    var circles : [Circle] = []
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        let con = UIGraphicsGetCurrentContext()
        guard let ctx = con else {
            return
        }
        
        ctx.clear(rect)
        ctx.setFillColor(UIColor.lightGray.cgColor)
        ctx.fill(rect)
        ctx.setStrokeColor(UIColor.red.cgColor)
        
        for circle in circles {
            ctx.strokeEllipse(in: CGRect(x: circle.x - circle.r, y: circle.y - circle.r, width: 2 * circle.r, height: 2 * circle.r))
        }
    }
 
    func render(circles : [Circle]) {
        self.circles = circles
        setNeedsDisplay();
    }
}
